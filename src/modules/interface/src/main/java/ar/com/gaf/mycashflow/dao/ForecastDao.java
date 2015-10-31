package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Forecast;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;

import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

public interface ForecastDao extends SavableEntityDao<Forecast>, UpdatableEntityDao<Forecast>, RemovableEntityDao<Forecast> {

    Forecast getById(Long forecastId);

    List<Forecast> getAll();

    List<ResultadoQuery> findSumForecastAgrupadosGrupoGastos(int anio);

    List<ResultadoQuery> findImporteTotalMes(int anio);

    List<ResultadoQuery> findDetalleForecastsPorGrupoGastos(String grupoGasto, int anio);

}