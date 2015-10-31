package ar.com.gaf.mycashflow.model.entities;

import ar.com.gaf.mycashflow.model.entities.base.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Entity
@Table(name = "FORECAST")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Forecast extends DefaultEntity<Long> implements Serializable {


    @SequenceGenerator(name = "FORECAST_SEQUENCE_GENERATOR", sequenceName = "FORECAST_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORECAST_SEQUENCE_GENERATOR")
    @Column(name = "ID_FORECAST")
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    private CentroCosto centroCosto;
    private TipoGasto tipoGasto;
    private Double importe;
    private String descripcion;
    private int mes;
    private int anio;



    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CENTRO_COSTO_FK", referencedColumnName = "ID_CENTRO_COSTO")
    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_GASTO_FK", referencedColumnName = "ID_TIPO_GASTO")
    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    @Column(name="IMPORTE",nullable=false)
    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @Column(name="DESCRIPCION",nullable=false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="MES",nullable=false)
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Column(name="ANIO",nullable=false)
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
