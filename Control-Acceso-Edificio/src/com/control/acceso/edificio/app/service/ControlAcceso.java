package com.control.acceso.edificio.app.service;

import com.control.acceso.edificio.app.exceptions.CredencialInvalidaException;
import com.control.acceso.edificio.app.exceptions.DatosInvalidosException;
import com.control.acceso.edificio.app.exceptions.HorarioNoPermitidoException;
import com.control.acceso.edificio.app.exceptions.UsuarioBloqueadoException;
import com.control.acceso.edificio.app.modelo.Usuario;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControlAcceso {
    private static final int MAX_INTENTOS = 3;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();
    private final Path archivoHistorial = Path.of("data", "access-log.csv");

    public ControlAcceso() {
        inicializarHistorial();
    }

    public void registrarUsuario(Usuario usuario) throws DatosInvalidosException {
        if (usuario == null) {
            throw new DatosInvalidosException("El usuario no puede ser nulo.");
        }
        if (usuarios.containsKey(usuario.getId())) {
            throw new DatosInvalidosException(
                    "Ya existe un usuario registrado con el ID " + usuario.getId() + ".");
        }

        usuarios.put(usuario.getId(), usuario);
        System.out.println("Usuario registrado correctamente.");
    }

    /**
     * Valida ID, estado, área y horario. El bloque finally registra siempre
     * el intento, incluso cuando una excepción se propaga al menú.
     */
    public void validarAcceso(String id, String area)
            throws CredencialInvalidaException,
            HorarioNoPermitidoException,
            UsuarioBloqueadoException {

        Usuario usuario = null;
        String resultado = "DENEGADO";
        String detalle = "Intento no completado";

        try {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("El ID no puede estar vacío.");
            }
            if (area == null || area.isBlank()) {
                throw new IllegalArgumentException("El área no puede estar vacía.");
            }

            usuario = usuarios.get(id.trim());

            if (usuario == null) {
                detalle = "Credencial no registrada";
                throw new CredencialInvalidaException(
                        "No existe una credencial registrada con ese ID.");
            }

            if (!usuario.isActivo()) {
                detalle = "Usuario bloqueado";
                throw new UsuarioBloqueadoException(
                        "El usuario está bloqueado. Solicite asistencia administrativa.");
            }

            if (!usuario.tienePermiso(area)) {
                usuario.registrarIntentoFallido();
                detalle = "Área no autorizada";
                if (!usuario.isActivo()) {
                    detalle = "Bloqueado por alcanzar " + MAX_INTENTOS + " intentos fallidos";
                    throw new UsuarioBloqueadoException(
                            "Usuario bloqueado por acumular " + MAX_INTENTOS
                                    + " intentos fallidos.");
                }
                throw new CredencialInvalidaException(
                        "El usuario no tiene permiso para el área " + area + ". Intentos: "
                                + usuario.getIntentosFallidos() + "/" + MAX_INTENTOS);
            }

            LocalTime horaActual = LocalTime.now();
            if (!usuario.estaDentroDelHorario(horaActual)) {
                usuario.registrarIntentoFallido();
                detalle = "Fuera del horario autorizado";
                if (!usuario.isActivo()) {
                    detalle = "Bloqueado por alcanzar " + MAX_INTENTOS + " intentos fallidos";
                    throw new UsuarioBloqueadoException(
                            "Usuario bloqueado por acumular " + MAX_INTENTOS
                                    + " intentos fallidos.");
                }
                throw new HorarioNoPermitidoException(
                        "Acceso fuera del horario autorizado ("
                                + usuario.getHoraInicio() + " - " + usuario.getHoraFin()
                                + "). Intentos: " + usuario.getIntentosFallidos()
                                + "/" + MAX_INTENTOS);
            }

            usuario.registrarAccesoExitoso();
            resultado = "AUTORIZADO";
            detalle = "Acceso permitido al área " + area.toUpperCase();
            System.out.println("ACCESO AUTORIZADO. Bienvenido/a, " + usuario.getNombre() + ".");

        } finally {
            // Se ejecuta tanto si el acceso fue autorizado como si hubo una excepción.
            registrarIntento(id, usuario == null ? "Desconocido" : usuario.getNombre(),
                    area, resultado, detalle);
        }
    }

    public void desbloquearUsuario(String id) throws CredencialInvalidaException {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new CredencialInvalidaException("No existe un usuario con ese ID.");
        }
        usuario.desbloquear();
        System.out.println("Usuario desbloqueado. Los intentos fallidos se reiniciaron.");
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n--- USUARIOS REGISTRADOS ---");
        for (Usuario usuario : usuarios.values()) {
            System.out.printf(
                    "ID: %s | Nombre: %s | Estado: %s | Intentos: %d/%d"
                            + " | Horario: %s-%s | Áreas: %s%n",
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.isActivo() ? "ACTIVO" : "BLOQUEADO",
                    usuario.getIntentosFallidos(),
                    MAX_INTENTOS,
                    usuario.getHoraInicio(),
                    usuario.getHoraFin(),
                    String.join(", ", usuario.getAreasPermitidas())
            );
        }
    }

    public void mostrarHistorial() {
        if (!Files.exists(archivoHistorial)) {
            System.out.println("Todavía no hay registros de acceso.");
            return;
        }

        try {
            System.out.println("\n--- HISTORIAL DE ACCESOS ---");
            Files.readAllLines(archivoHistorial, StandardCharsets.UTF_8)
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("No fue posible leer el historial: " + e.getMessage());
        }
    }

    private void inicializarHistorial() {
        try {
            Files.createDirectories(archivoHistorial.getParent());
            if (Files.notExists(archivoHistorial)) {
                Files.writeString(archivoHistorial,
                        "fecha_hora,id,nombre,area,resultado,detalle"
                                + System.lineSeparator(),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.err.println("Advertencia: no se pudo inicializar el historial: "
                    + e.getMessage());
        }
    }

    private void registrarIntento(String id, String nombre, String area,
                                  String resultado, String detalle) {
        LocalDateTime ahora = LocalDateTime.now();
        String linea = String.join(",",
                escapar(FORMATO_FECHA.format(ahora)),
                escapar(id),
                escapar(nombre),
                escapar(area),
                escapar(resultado),
                escapar(detalle));

        try {
            Files.createDirectories(archivoHistorial.getParent());
            Files.writeString(archivoHistorial,
                    linea + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.printf("Registro: %s | %s | %s%n", resultado, id, detalle);
        } catch (IOException e) {
            // La falla de auditoría se informa; no se oculta.
            System.err.println("ERROR: no se pudo guardar el registro de acceso: "
                    + e.getMessage());
        }
    }

    /** Escapado sencillo para mantener cada evento en una sola línea CSV. */
    private String escapar(String valor) {
        String seguro = valor == null ? "" : valor.replace("\"", "\"\"");
        return "\"" + seguro + "\"";
    }

    public Collection<Usuario> obtenerUsuarios() {
        return java.util.List.copyOf(usuarios.values());
    }
}