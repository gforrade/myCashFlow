package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.AbstractEntityDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Forecast;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;
import ar.com.gaf.mycashflow.model.entities.GrupoGasto;
import lombok.extern.log4j.Log4j;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@Stateless
public class ForecastDaoBean extends AbstractEntityDao<Forecast> implements ForecastDao {

    @Override
    public Forecast getById(Long gastoId) {
       return entityManager.find(Forecast.class, gastoId);
    }


    @Override
    public List<Forecast> getAll() {
        return entityManager.createQuery(
                "select g " +
                        "from Foreacst f " +
                        "order by f.anio", Forecast.class)
                .getResultList();
    }



    @Override
    public List<ResultadoQuery> findSumForecastAgrupadosGrupoGastos( int anio) {
        //todo cambiarlo
        Query query = entityManager.createNamedQuery("findSumGastosAgrupadosGrupoGastos",GastoEfectivo.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof GrupoGasto) {
                resultadoQuery.setNombre(((GrupoGasto)resultado[0]).name());
            }
            if (resultado[1] instanceof Integer) {
                resultadoQuery.setMes((Integer) resultado[1]);
            }

            if (resultado[2] instanceof Double) {
                resultadoQuery.setImporte((Double)resultado[2]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }



    @Override
    public List<ResultadoQuery> findDetalleForecastsPorGrupoGastos(String grupoGasto, int anio) {
        Query query = entityManager.createNamedQuery("findDetalleGastosPorGrupoGastos",GastoEfectivo.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("grupoGasto", getEnumFromString(GrupoGasto.class, grupoGasto));
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof String) {
                resultadoQuery.setNombre((String) resultado[0]);
            }

            if (resultado[1] instanceof Integer) {
                resultadoQuery.setMes((Integer) resultado[1]);
            }

            if (resultado[2] instanceof Double) {
                resultadoQuery.setImporte((Double)resultado[2]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }
    //Todo llevar a una clase Util
    private  <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }

    @Override
    public List<ResultadoQuery> findImporteTotalMes(int anio) {
        Query query = entityManager.createNamedQuery("findImporteTotalMes",GastoEfectivo.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof Integer) {
                resultadoQuery.setMes((Integer) resultado[0]);
            }

            if (resultado[1] instanceof Double) {
                resultadoQuery.setImporte((Double)resultado[1]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }
}
