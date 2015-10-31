package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.AbstractEntityDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import lombok.extern.log4j.Log4j;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@Stateless
public class GastoTarjetaDaoBean extends AbstractEntityDao<GastoTarjetaCredito> implements GastoTarjetaCreditoDao {

    @Override
    public GastoTarjetaCredito getById(Long gastoId) {
        return entityManager.find(GastoTarjetaCredito.class,gastoId);
    }

    @Override
    public List<GastoTarjetaCredito> findByMonth(int month) {
        return null;
    }

    @Override
    public List<GastoTarjetaCredito> findByYear(int year) {
        return null;
    }

    @Override
    public List<GastoTarjetaCredito> getAll() {
        return entityManager.createQuery(
                "select gtc " +
                        "from GastoTarjetaCredito gtc " +
                        "order by gtc.fechaCompra", GastoTarjetaCredito.class)
                .getResultList();
    }

    public List<GastoTarjetaCredito> findAllGastosTarjetaFromMesAnio(int mes, int anio) {

        TypedQuery<GastoTarjetaCredito> query = entityManager.createNamedQuery("findAllGastosByMesAnio",GastoTarjetaCredito.class);

        query.setParameter("mes", mes);
        query.setParameter("anio", anio);


        return query.getResultList();
    }

    @Override
    public List<ResultadoQuery> findSumGastosAgrupadosCentroCosto(int mes, int anio) {
        Query  query = entityManager.createNamedQuery("findSumGastosAgrupadosCentroCosto",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("mes", mes);
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof String) {
                resultadoQuery.setNombre((String) resultado[0]);
            }
            if (resultado[1] instanceof Double) {
                resultadoQuery.setImporte((Double)resultado[1]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }

    public List<ResultadoQuery> findGastosAgrupadosTarjetaAnual(int anio) {
        Query  query = entityManager.createNamedQuery("findGastosAgrupadosTarjetaAnual",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof String) {
                resultadoQuery.setNombre((String)resultado[0]);
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
    public List<ResultadoQuery> findGastosByTarjetaMesAnio(String tarjeta, int anio) {
        Query  query = entityManager.createNamedQuery("findGastosByTarjetaMesAnio",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);
        query.setParameter("tarjeta", tarjeta);
        List results = query.getResultList();

        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof Long) {
                resultadoQuery.setId((Long) resultado[0]);
            }
            if (resultado[1] instanceof String) {
                resultadoQuery.setNombre((String)resultado[1]);
            }
            if (resultado[2] instanceof Integer) {
                resultadoQuery.setMes((Integer) resultado[2]);
            }

            if (resultado[3] instanceof Double) {
                resultadoQuery.setImporte((Double)resultado[3]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;

    }

    @Override
    public List<ResultadoQuery> findGastosAgrupadosCentroCostosAnual( int anio) {
        Query  query = entityManager.createNamedQuery("findGastosAgrupadosCentroCostosAnual",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);

        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof String) {
                resultadoQuery.setNombre((String)resultado[0]);
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
    public List<ResultadoQuery> findGastosByCentroCostoMesAnio(String centroCosto, int anio) {
        Query  query = entityManager.createNamedQuery("findGastosByCentroCostoMesAnio",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);
        query.setParameter("centroCosto", centroCosto);
        List results = query.getResultList();

        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof Long) {
                resultadoQuery.setId((Long) resultado[0]);
            }
            if (resultado[1] instanceof String) {
                resultadoQuery.setNombre((String)resultado[1]);
            }
            if (resultado[2] instanceof Integer) {
                resultadoQuery.setMes((Integer) resultado[2]);
            }

            if (resultado[3] instanceof Double) {
                resultadoQuery.setImporte((Double) resultado[3]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }

    @Override
    public List<ResultadoQuery> getTotalTarjetasMesCentroCosto(int mes, int anio) {
        Query  query = entityManager.createNamedQuery("getTotalTarjetasMesCentroCosto",GastoTarjetaCredito.class);
        List<ResultadoQuery> resultadoQueryList = new ArrayList<>();
        query.setParameter("anio", anio);
        query.setParameter("mes", mes);
        List results = query.getResultList();
        for (Object result : results) {
            Object[] resultado = (Object[]) result;
            ResultadoQuery resultadoQuery = new ResultadoQuery();
            if (resultado[0] instanceof Long) {
                resultadoQuery.setId((Long) resultado[0]);
            }
            if (resultado[1] instanceof Double) {
                resultadoQuery.setImporte((Double) resultado[1]);
            }
            resultadoQueryList.add(resultadoQuery);
        }

        return resultadoQueryList;
    }

}
