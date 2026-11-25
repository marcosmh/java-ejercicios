package com.sistema.control.acceso.app;

import com.sistema.control.acceso.app.excepciones.CredencialInvalidaException;
import com.sistema.control.acceso.app.excepciones.DatosIncompletosException;
import com.sistema.control.acceso.app.excepciones.HorarioNoPermitidoException;
import com.sistema.control.acceso.app.excepciones.UsuarioBloqueadoException;
import com.sistema.control.acceso.app.modelo.EstadoUsuario;
import com.sistema.control.acceso.app.modelo.RegistroIntento;
import com.sistema.control.acceso.app.modelo.Usuario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ControlAcceso {

    private final Map<String, Usuario> usuariosPorId = new LinkedHashMap<>();
    private final List<RegistroIntento> bitacora = new ArrayList<>();

    public void registrarUsuario(Usuario usuario) throws DatosIncompletosException {
        if (usuario == null) {
            throw new DatosIncompletosException("El objeto usuario no puede ser nulo.");
        }
        if (usuariosPorId.containsKey(usuario.getId())) {
            throw new DatosIncompletosException(
                    "Ya existe un usuario registrado con el ID: " + usuario.getId());
        }
        usuariosPorId.put(usuario.getId(), usuario);
    }

    public void bloquearUsuario(String idUsuario) throws CredencialInvalidaException {
        Usuario usuario = buscarUsuario(idUsuario);
        usuario.bloquear();
    }

    public void desbloquearUsuario(String idUsuario) throws CredencialInvalidaException {
        Usuario usuario = buscarUsuario(idUsuario);
        usuario.desbloquear();
    }

    private Usuario buscarUsuario(String idUsuario) throws CredencialInvalidaException {
        if (idUsuario == null || idUsuario.isBlank()) {
            throw new CredencialInvalidaException("La credencial/ID de acceso está vacía.");
        }
        Usuario usuario = usuariosPorId.get(idUsuario.trim());
        if (usuario == null) {
            throw new CredencialInvalidaException(
                    "Credencial no registrada o inválida: " + idUsuario.trim());
        }
        return usuario;
    }

    public boolean validarAcceso(String idUsuario, String areaSolicitada, LocalTime horaAcceso)
            throws CredencialInvalidaException, HorarioNoPermitidoException,
            UsuarioBloqueadoException, DatosIncompletosException {

        boolean accesoConcedido = false;
        String motivoRegistro = "Intento no procesado";
        String idLimpio = (idUsuario == null || idUsuario.isBlank()) ? "SIN_ID" : idUsuario.trim();
        String areaLimpia = (areaSolicitada == null || areaSolicitada.isBlank())
                ? "AREA_NO_ESPECIFICADA" : areaSolicitada.trim();

        try {
            if (idUsuario == null || idUsuario.isBlank()
                    || areaSolicitada == null || areaSolicitada.isBlank()
                    || horaAcceso == null) {
                throw new DatosIncompletosException(
                        "Datos incompletos en la solicitud de acceso (ID, área u hora faltante).");
            }

            Usuario usuario = buscarUsuario(idLimpio);

            if (usuario.getEstado() == EstadoUsuario.BLOQUEADO) {
                throw new UsuarioBloqueadoException(
                        "Acceso denegado: el usuario '" + usuario.getNombre()
                                + "' (" + idLimpio + ") está BLOQUEADO por intentos fallidos.");
            }

            if (!usuario.estaDentroDeHorario(horaAcceso)) {
                usuario.registrarIntentoFallido();
                String extraBloqueo = (usuario.getEstado() == EstadoUsuario.BLOQUEADO)
                        ? " -> Usuario BLOQUEADO tras alcanzar el límite de intentos."
                        : " (Intentos fallidos: " + usuario.getIntentosFallidos() + "/"
                        + Usuario.MAX_INTENTOS_FALLIDOS + ")";
                throw new HorarioNoPermitidoException(
                        "Acceso denegado a '" + areaLimpia + "' a las " + horaAcceso
                                + ". Horario permitido: " + usuario.getHoraInicioPermitida()
                                + " - " + usuario.getHoraFinPermitida() + extraBloqueo);
            }

            usuario.reiniciarIntentosFallidos();
            accesoConcedido = true;
            motivoRegistro = "Acceso concedido a " + usuario.getNombre() + " (" + areaLimpia + ")";
            return true;

        } catch (CredencialInvalidaException ex) {
            motivoRegistro = ex.getMessage();
            throw ex;
        } catch (UsuarioBloqueadoException ex) {
            motivoRegistro = ex.getMessage();
            throw ex;
        } catch (HorarioNoPermitidoException ex) {
            motivoRegistro = ex.getMessage();
            throw ex;
        } catch (DatosIncompletosException ex) {
            motivoRegistro = ex.getMessage();
            throw ex;
        } finally {
            // Se ejecuta siempre: registra tanto accesos exitosos como fallidos
            bitacora.add(new RegistroIntento(null,idLimpio, areaLimpia, accesoConcedido, motivoRegistro));

        }
    }

    public List<RegistroIntento> getBitacoraCompleta() {
        return Collections.unmodifiableList(bitacora);
    }

    public List<RegistroIntento> getAccesosFallidos() {
        List<RegistroIntento> fallidos = new ArrayList<>();
        for (RegistroIntento r : bitacora) {
            if (!r.isExitoso()) {
                fallidos.add(r);
            }
        }
        return fallidos;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuariosPorId.values());
    }


}
