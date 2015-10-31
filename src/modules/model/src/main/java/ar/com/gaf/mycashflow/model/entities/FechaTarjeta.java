package ar.com.gaf.mycashflow.model.entities;

import ar.com.gaf.mycashflow.model.entities.base.DefaultEntity;


import java.util.Date;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
public class FechaTarjeta extends DefaultEntity<Long> {

    private int anio;
    private int mes;

    private Date fechaCierre;
    private Date fechaVencimiento;

    public FechaTarjeta(int anio, int mes, Date fechaCierre, Date fechaVencimiento) {
        this.anio = anio;
        this.mes = mes;
        this.fechaCierre = fechaCierre;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
