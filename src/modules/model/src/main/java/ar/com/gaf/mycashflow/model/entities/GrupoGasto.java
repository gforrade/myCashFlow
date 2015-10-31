package ar.com.gaf.mycashflow.model.entities;

import ar.com.gaf.mycashflow.model.entities.base.DefaultEntity;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
public enum GrupoGasto {
    VIVIENDA,
    TRANSPORTE,
    IMPUESTOS,
    EDUCACION,
    TARJETAS_CREDITO,
    ESPARCIMIENTO,
    ALIMENTACION,
    PRESTAMOS,
    SALUD,
    EXTRAS;

    public static Long getId(GrupoGasto grupoGasto){
        if (grupoGasto.name().equals("VIVIENDA")) return new Long(1);
        if (grupoGasto.name().equals("TRANSPORTE")) return new Long(2);
        if (grupoGasto.name().equals("IMPUESTOS")) return new Long(3);
        if (grupoGasto.name().equals("EDUCACION")) return new Long(4);
        if (grupoGasto.name().equals("TARJETAS_CREDITO")) return new Long(5);
        if (grupoGasto.name().equals("ESPARCIMIENTO")) return new Long(6);
        if (grupoGasto.name().equals("ALIMENTACION")) return new Long(7);
        if (grupoGasto.name().equals("PRESTAMOS")) return new Long(8);
        if (grupoGasto.name().equals("SALUD")) return new Long(9);
        if (grupoGasto.name().equals("EXTRAS")) return new Long(10);
        else return null;
    }

    public static String getName(Long id){
        if (id == 1)  return  VIVIENDA.name();
        if (id == 2)  return  TRANSPORTE.name();
        if (id == 3)  return  IMPUESTOS.name();
        if (id == 4)  return  EDUCACION.name();
        if (id == 5)  return  TARJETAS_CREDITO.name();
        if (id == 6)  return  ESPARCIMIENTO.name();
        if (id == 7)  return  ALIMENTACION.name();
        if (id == 8)  return  PRESTAMOS.name();
        if (id == 9)  return  SALUD.name();
        if (id == 10)  return  EXTRAS.name();
        else return null;
    }

}

