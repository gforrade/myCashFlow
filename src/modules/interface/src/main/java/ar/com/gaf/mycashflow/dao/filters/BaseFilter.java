package ar.com.gaf.mycashflow.dao.filters;

import ar.com.gaf.mycashflow.dao.filters.commons.OrderExpression;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
public class BaseFilter implements Serializable{

    public enum OrderElement {
        NOMBRE,
        DESCRIPCION
    }

    private static final long serialVersionUID = -517152775729635L;

    private String nombre;
    private String descripcion;
    private String grupoGasto;

    private List<OrderExpression<OrderElement>> orderBy;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrupoGasto() {
        return grupoGasto;
    }

    public void setGrupoGasto(String grupoGasto) {
        this.grupoGasto = grupoGasto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OrderExpression<OrderElement>> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<OrderExpression<OrderElement>> orderBy) {
        this.orderBy = orderBy;
    }

}
