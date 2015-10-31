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
@Table(name = "GASTO")
@Inheritance
@DiscriminatorColumn(name="GASTO_TYPE_CODE")
@EqualsAndHashCode(callSuper = true)
@ToString

public class Gasto extends DefaultEntity<Long> implements Serializable {

    static public enum Type {
        TARJETA(GastoTarjetaCredito.class),
        EFECTIVO(GastoEfectivo.class);

        private Class<? extends Gasto> referencedClass;

        private  Type(Class<? extends Gasto> referencedClass) {
            this.referencedClass = referencedClass;
        }

        public Class<? extends Gasto> getReferencedClass() {
            return referencedClass;
        }
    }

    @SequenceGenerator(name = "GASTO_SEQUENCE_GENERATOR", sequenceName = "GASTO_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GASTO_SEQUENCE_GENERATOR")
    @Column(name = "ID_GASTO")
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
    private Date fechaCompra;
    private String descripcion;
    private Moneda moneda;
    protected int dia;
    private int mes;
    private int anio;

    public Gasto() {
    }

    public Gasto(CentroCosto centroCosto, TipoGasto tipoGasto, Double importe,Date fechaCompra, String descripcion, Moneda moneda) {
        this.centroCosto = centroCosto;
        this.tipoGasto = tipoGasto;
        this.importe = importe;
        setFechaCompra(fechaCompra);
        this.descripcion  = descripcion;
        this.moneda = moneda;
    }

    public Gasto(CentroCosto centroCosto, Double importe,Date fechaCompra, String descripcion, Moneda moneda) {
        this.centroCosto = centroCosto;
        this.importe = importe;
        setFechaCompra(fechaCompra);
        this.descripcion  = descripcion;
        this.moneda = moneda;
    }


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

    @Column(name="FECHA_COMPRA",nullable=false)
    @Temporal(TemporalType.DATE)
    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaCompra);
        anio = cal.get(Calendar.YEAR);
        //el mes arranca desde cero
        // The first month of the year in the Gregorian and Julian calendars is JANUARY which is 0
        dia = cal.get(Calendar.DAY_OF_MONTH);
        setMes( cal.get(Calendar.MONTH)+1);
    }

    @Column(name="DESCRIPCION",nullable=false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "MONEDA", nullable=true, length=10)
    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    // Para referenciar al DiscriminatorColumn desde JPQL del JPA 2.0 se usa TYPE(Gasto)
    @Transient
    public Type getType() {
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : Type.valueOf(val.value());
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

    @Transient
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
