package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Forecast;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface ForecastService {

    List<Forecast> getAll();

    GastoEfectivo getById(Long id);

    void save(Forecast forecast, int meses);

    void save(Forecast forecast);

    Forecast update(Forecast forecast);

    void remove(Forecast forecast);

    List<ResultadoQuery> findSumForecastAgrupadosGrupoGastos(int anio);

    List<ResultadoQuery> findImporteTotalMes(int anio);

    List<ResultadoQuery> findDetalleForecastsPorGrupoGastos(String grupoGasto, int anio);


}
