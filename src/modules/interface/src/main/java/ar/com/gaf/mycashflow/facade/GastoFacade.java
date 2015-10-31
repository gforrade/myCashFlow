package ar.com.gaf.mycashflow.facade;

import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.SelectValue;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.Moneda;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;


/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Local
public interface GastoFacade {

     List<SelectValue> getCentrosCosto();

     List<SelectValue> getTiposGasto();

     List<SelectValue> getTiposGasto(String grupoGasto);

     List<SelectValue> getTarjetasCredito();

     List<SelectValue> getMonedas();

     List<SelectValue> getGruposGasto();

     //borrar
     void addGasto(CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGasto(String centroCosto, String tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGasto(Long centroCostoId, Long tipoGastoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGastoTarjetaCredito(TarjetaCreditoEnum tarjetaCreditoEnum, CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     //borrar
     void addGastoTarjetaCredito(String tarjetaCredito, String centroCosto, String tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGastoTarjetaCredito(Long tarjetaCreditoId, Long centroCostoId, Long tipoGastoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

     void addGastoTarjetaCredito(Long tarjetaCreditoId, Long centroCostoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda,  int cantidadMeses);

}
