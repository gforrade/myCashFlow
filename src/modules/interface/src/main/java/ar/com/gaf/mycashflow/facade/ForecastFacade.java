package ar.com.gaf.mycashflow.facade;

import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.FilaResultado;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.Moneda;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Local
public interface ForecastFacade {

     List<FilaResultado> findSumGastosAgrupadosGrupoGastos(int anio);

     List<FilaResultado> findDetalleGastosPorGrupoGastos(String grupoGasto,int anio);

     List<FilaResultado> getTotalTarjetasMesCentroCosto(int mes ,int anio);

     void actualizarGastosTarjetas(int mes, int anio);

}
