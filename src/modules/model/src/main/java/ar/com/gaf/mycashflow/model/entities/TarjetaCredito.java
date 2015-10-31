package ar.com.gaf.mycashflow.model.entities;

import ar.com.gaf.mycashflow.model.entities.base.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Entity
@Table(name = "TARJETA_CREDITO")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class TarjetaCredito extends DefaultEntity<Long> {

    private String nombre;
    private String descripcion;
    private String bancoEmisor;
    private List<FechaTarjeta> fechas;

    @SequenceGenerator(name = "TARJETA_CREDITO_SEQUENCE_GENERATOR", sequenceName = "TARJETA_CREDITO_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARJETA_CREDITO_SEQUENCE_GENERATOR")
    @Column(name = "ID_TARJETA_CREDITO")
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }


    @Column(name = "NOMBRE", nullable=false,length=250)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "DESCRIPCION", nullable=false,length=250)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "BANCO_EMISOR", nullable=false,length=250)
    public String getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(String bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    @Transient
    public List<FechaTarjeta> getFechas() {
        return fechas;
    }

    public void setFechas(List<FechaTarjeta> fechas) {
        this.fechas = fechas;
    }

    public int getCurrentDayCorte() {
        //retorna el dia de corte del mes en curso
        //se calculara en base a la info de fechas
        //Todo implementar esto
        return 23;
    }
}
