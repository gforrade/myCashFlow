package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;

import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

public interface GastoDao extends SavableEntityDao<GastoEfectivo>, UpdatableEntityDao<GastoEfectivo>, RemovableEntityDao<GastoEfectivo> {
    GastoEfectivo getById(Long gastoId);

    List<GastoEfectivo> findByMonth(int month);

    List<GastoEfectivo> findByYear(int year);

    List<GastoEfectivo> getAll();

    List<ResultadoQuery> findSumGastosAgrupadosGrupoGastos(int anio);

    List<ResultadoQuery> findImporteTotalMes(int anio);

    List<ResultadoQuery> findDetalleGastosPorGrupoGastos(String grupoGasto, int anio);

    GastoEfectivo findByCentroCostoIdMesAnio(Long idCentroCosto, int mes, int anio);

}