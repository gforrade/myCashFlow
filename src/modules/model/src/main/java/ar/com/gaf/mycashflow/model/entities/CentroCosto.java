package ar.com.gaf.mycashflow.model.entities;

import ar.com.gaf.mycashflow.model.entities.base.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Entity
@Table(name = "CENTRO_COSTO")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CentroCosto extends DefaultEntity<Long> {

    private String nombre;
    private String descripcion;

    @SequenceGenerator(name = "CENTRO_COSTO_SEQUENCE_GENERATOR", sequenceName = "CENTRO_COSTO_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CENTRO_COSTO_SEQUENCE_GENERATOR")
    @Column(name = "ID_CENTRO_COSTO")
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
}
