package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;

import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

public interface GastoTarjetaCreditoDao extends SavableEntityDao<GastoTarjetaCredito>, UpdatableEntityDao<GastoTarjetaCredito>, RemovableEntityDao<GastoTarjetaCredito> {

    GastoTarjetaCredito getById(Long gastoId);

    List<GastoTarjetaCredito> findByMonth(int month);

    List<GastoTarjetaCredito> findByYear(int year);

    List<GastoTarjetaCredito> getAll();

    List<GastoTarjetaCredito> findAllGastosTarjetaFromMesAnio(int mes, int anio) ;

    List<ResultadoQuery> findSumGastosAgrupadosCentroCosto(int mes, int anio) ;

    List<ResultadoQuery> findGastosAgrupadosTarjetaAnual(int anio) ;

    List<ResultadoQuery> findGastosByTarjetaMesAnio(String tarjeta, int anio) ;

    List<ResultadoQuery> findGastosAgrupadosCentroCostosAnual(int anio) ;

    List<ResultadoQuery> findGastosByCentroCostoMesAnio(String centroCosto,int anio) ;

    List<ResultadoQuery> getTotalTarjetasMesCentroCosto(int mes,int anio) ;


}