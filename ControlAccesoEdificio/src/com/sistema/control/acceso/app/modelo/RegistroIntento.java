package com.sistema.control.acceso.app.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroIntento {
    private LocalDateTime fechaHora;
    private String credencialId;
    private String area;
    private boolean exitoso;
    private String detalle;

    public RegistroIntento(LocalDateTime fechaHora, String credencialId, String area, boolean exitoso, String detalle) {
        this.fechaHora = fechaHora;
        this.credencialId = credencialId;
        this.area = area;
        this.exitoso = exitoso;
        this.detalle = detalle;
    }

    public boolean isExitoso() {
        return exitoso;
    }



    public void mostraInfo() {
        String estado = "FALLIDO";
        if(exitoso) {
            estado = "EXITOSO";
        }
        String fecha = fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String info = String.format("[%s] [%s] ID=%s | Área=%s | %s", fecha, estado, credencialId, area, detalle);
        System.out.println(info);

    }


}
