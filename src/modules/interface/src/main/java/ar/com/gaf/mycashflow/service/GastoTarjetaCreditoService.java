package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface GastoTarjetaCreditoService {
    List<GastoTarjetaCredito> getAll();
    GastoTarjetaCredito getById(Long id);

    void save(GastoTarjetaCredito gastoTarjetaCredito);
    GastoTarjetaCredito update(GastoTarjetaCredito gastoTarjetaCredito);
    void remove(GastoTarjetaCredito gastoTarjetaCredito);

    List<GastoTarjetaCredito> findAllGastosTarjetaFromMesAnio(int mes, int anio);

    List<ResultadoQuery> findSumGastosAgrupadosCentroCosto(int mes, int anio);

    List<ResultadoQuery> findGastosAgrupadosTarjetaAnual(int anio);

    List<ResultadoQuery> findGastosByTarjetaMesAnio(String tarjeta, int anio) ;

    List<ResultadoQuery> findGastosAgrupadosCentroCostosAnual( int anio) ;

    List<ResultadoQuery> findGastosByCentroCostoMesAnio(String centroCosto, int anio) ;

    List<ResultadoQuery> getTotalTarjetasMesCentroCosto(int mes, int anio) ;

}
