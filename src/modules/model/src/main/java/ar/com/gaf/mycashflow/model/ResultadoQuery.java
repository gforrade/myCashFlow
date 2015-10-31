package ar.com.gaf.mycashflow.model;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
public class ResultadoQuery {
    private Long id;
    private String nombre;
    private Integer mes;
    private Double importe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }
}
