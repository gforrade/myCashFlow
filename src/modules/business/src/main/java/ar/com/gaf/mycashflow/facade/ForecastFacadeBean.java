package ar.com.gaf.mycashflow.facade;

import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.*;
import ar.com.gaf.mycashflow.model.entities.*;
import ar.com.gaf.mycashflow.service.*;
import lombok.extern.log4j.Log4j;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@Stateless
public class ForecastFacadeBean implements ForecastFacade {

    @EJB
    private  GastoService gastoService;

    @EJB
    private  GastoTarjetaCreditoService gastoTarjetaCreditoService;

    @Override
    public List<FilaResultado> findSumGastosAgrupadosGrupoGastos(int anio) {
        List<ResultadoQuery> resultadoQueryList = new ArrayList<ResultadoQuery>();
        resultadoQueryList = gastoService.findSumGastosAgrupadosGrupoGastos(anio);
        List<FilaResultado> filaResultados = buildResultado(resultadoQueryList);
        FilaResultado filaTotales = buildTotalMes(anio);
        filaResultados.add(filaTotales);
        return filaResultados;
    }



    private  List<FilaResultado> buildResultado(List<ResultadoQuery> resultadoQueryList) {
        List<FilaResultado> listaResultado = new ArrayList<>();
        String concepto = null;
        Long currentId = null;
        Double totalImporteGrupo = null;
        Map<Integer,Double> meses = new HashMap<>();
        if (resultadoQueryList != null) {
            ResultadoQuery resultadoQuery = resultadoQueryList.get(0);
            concepto = resultadoQuery.getNombre();
            currentId = resultadoQuery.getId();
        }
        if (resultadoQueryList != null) {
            for (ResultadoQuery resultadoQuery : resultadoQueryList) {
                if (resultadoQuery.getNombre().equals(concepto)) {
                    meses.put(resultadoQuery.getMes(),resultadoQuery.getImporte());
                } else {
                    FilaResultado filaResultado = new FilaResultado();
                    if (currentId != null) {
                        filaResultado.setId(currentId);
                    }
                    filaResultado.setConcepto(concepto);
                    filaResultado.setImportes(meses);
                    listaResultado.add(filaResultado);
                    //nuevo concepto
                    concepto = resultadoQuery.getNombre();
                    currentId = resultadoQuery.getId();
                    meses = new HashMap<>();
                    meses.put(resultadoQuery.getMes(),resultadoQuery.getImporte());
                };
            }
            FilaResultado filaResultado = new FilaResultado();
            filaResultado.setConcepto(concepto);
            filaResultado.setImportes(meses);
            listaResultado.add(filaResultado);
        }
        return listaResultado;
    }

    @Override
    public List<FilaResultado> findDetalleGastosPorGrupoGastos(String grupoGasto, int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoService.findDetalleGastosPorGrupoGastos(grupoGasto,anio);
        return buildResultado(resultadoQueryList);
    }

    private FilaResultado buildTotalMes(int anio) {
        List<ResultadoQuery> resultadoQueryList = new ArrayList<ResultadoQuery>();
        resultadoQueryList = gastoService.findImporteTotalMes(anio);
        FilaResultado filaResultado = new FilaResultado();
        filaResultado.setConcepto("Total Mensual");
        Map<Integer,Double> meses = new HashMap<>();
        for (ResultadoQuery resultadoQuery : resultadoQueryList) {
            meses.put(resultadoQuery.getMes(),resultadoQuery.getImporte());
        }
        filaResultado.setImportes(meses);
        return filaResultado;
    }

    @Override
    public List<FilaResultado> getTotalTarjetasMesCentroCosto(int mes, int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoTarjetaCreditoService.getTotalTarjetasMesCentroCosto(mes,anio);
        return buildResultado(resultadoQueryList);
    }

    @Override
    public void actualizarGastosTarjetas(int mes, int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoTarjetaCreditoService.getTotalTarjetasMesCentroCosto(mes,anio);
        for (ResultadoQuery resultadoQuery : resultadoQueryList) {
            Long idCentroCosto = resultadoQuery.getId();
            gastoService.updateGastoTarjetaCentroCostoMesAnio(idCentroCosto,resultadoQuery.getImporte(),mes,anio);
        }
    }


}
