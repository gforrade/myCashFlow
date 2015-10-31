package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface GastoService {

    List<GastoEfectivo> getAll();

    GastoEfectivo getById(Long id);

    void save(GastoEfectivo gasto, int meses);

    void save(GastoEfectivo gasto);

    GastoEfectivo update(GastoEfectivo gasto);

    void remove(GastoEfectivo gasto);

    List<ResultadoQuery> findSumGastosAgrupadosGrupoGastos(int anio);

    List<ResultadoQuery> findImporteTotalMes(int anio);

    List<ResultadoQuery> findDetalleGastosPorGrupoGastos(String grupoGasto, int anio);

    void updateGastoTarjetaCentroCostoMesAnio(Long idCentroCosto,Double importe,  int mes, int anio);


}
