package ar.com.gaf.mycashflow.facade;

import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.FilaResultado;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.Moneda;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Local
public interface CashFlowFacade {

     void addGasto(CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGastoTarjetaCredito(TarjetaCreditoEnum tarjetaCreditoEnum, CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     List<FilaResultado> findGastosAgrupadosTarjetaAnual(int anio);

     List<FilaResultado> findGastosByTarjetaMesAnio(String tarjeta, int anio);

     List<FilaResultado> findGastosAgrupadosCentroCostosAnualList(int anio);

     GastoTarjetaCredito getById(Long id);

     List<FilaResultado> findGastosAgrupadosCentroCostosAnual(String centroCosto, int anio);

}
