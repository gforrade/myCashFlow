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
import java.text.CollationElementIterator;
import java.util.*;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@Stateless
public class CashFlowFacadeBean implements CashFlowFacade {

    @EJB
    private  CentroCostoService centroCostoService;
    @EJB
    private  TarjetaCreditoService tarjetaCreditoService;
    @EJB
    private  GastoService gastoService;
    @EJB
    private  TipoGastoService tipoGastoService;
    @EJB
    private  GastoTarjetaCreditoService gastoTarjetaCreditoService;


    @Override
    public void addGasto(CentroCostoEnum centroCostoEnum,TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
            createGasto(getCentroCosto(centroCostoEnum.name()), getTipoGasto(tipoGasto.name()), importe, fechaCompra, descripcion, moneda, cantidadMeses);
    }

    @Override
    public void addGastoTarjetaCredito(TarjetaCreditoEnum tarjetaCreditoEnum, CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        Gasto gasto = new Gasto(getCentroCosto(centroCostoEnum.name()), getTipoGasto(tipoGasto.name()), importe,  fechaCompra, descripcion, Moneda.PESOS);
        createGastoTarjeta(gasto, getTarjetaCredito(tarjetaCreditoEnum.name()), cantidadMeses);

    }

    @Override
    public List<FilaResultado> findGastosAgrupadosTarjetaAnual(int anio) {
        List<ResultadoQuery> resultadoQueryList = new ArrayList<ResultadoQuery>();
        resultadoQueryList = gastoTarjetaCreditoService.findGastosAgrupadosTarjetaAnual(anio);
        return buildResultado(resultadoQueryList);
    }


    @Override
    public List<FilaResultado> findGastosByTarjetaMesAnio(String tarjeta, int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoTarjetaCreditoService.findGastosByTarjetaMesAnio(tarjeta, anio);
        return buildResultado(resultadoQueryList);
    }

    @Override
    public List<FilaResultado> findGastosAgrupadosCentroCostosAnualList(int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoTarjetaCreditoService.findGastosAgrupadosCentroCostosAnual(anio);
        return buildResultado(resultadoQueryList);
    }

    @Override
    public List<FilaResultado> findGastosAgrupadosCentroCostosAnual(String centroCosto, int anio) {
        List<ResultadoQuery> resultadoQueryList = gastoTarjetaCreditoService.findGastosByCentroCostoMesAnio(centroCosto,anio);
        return buildResultado(resultadoQueryList);
    }


    @Override
    public GastoTarjetaCredito getById(Long id) {
        return gastoTarjetaCreditoService.getById(id);
    }

    private  List<FilaResultado> buildResultado(List<ResultadoQuery> resultadoQueryList) {
        List<FilaResultado> listaResultado = new ArrayList<>();
        String concepto = null;
        Long currentId = null;
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
            if (currentId != null) {
                filaResultado.setId(currentId);
            }
            filaResultado.setConcepto(concepto);
            filaResultado.setImportes(meses);
            listaResultado.add(filaResultado);
        }
        return listaResultado;
    }



    private void createGasto(CentroCosto centroCosto, TipoGasto tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda , int meses) {
        GastoEfectivo gasto = new GastoEfectivo(centroCosto,tipoGasto,importe,fechaCompra,descripcion,moneda);
        if (meses == 1) {
            gastoService.save(gasto);
        } else {
            gastoService.save(gasto,meses);
        }
    }

    private void createGastoTarjeta(Gasto gasto, TarjetaCredito tarjetaCredito, int nroCuotas ) {
        GastoTarjetaCredito gastoTarjetaCredito = new GastoTarjetaCredito(tarjetaCredito,nroCuotas);
        gastoTarjetaCredito.setCentroCosto(gasto.getCentroCosto());
        gastoTarjetaCredito.setDescripcion(gasto.getDescripcion());
        gastoTarjetaCredito.setFechaCompra(gasto.getFechaCompra());
        gastoTarjetaCredito.setImporte(gasto.getImporte());
        gastoTarjetaCredito.setMoneda(gasto.getMoneda());
        gastoTarjetaCredito.setTipoGasto(gasto.getTipoGasto());
        gastoTarjetaCreditoService.save(gastoTarjetaCredito);
    }

    private  CentroCosto getCentroCosto(String nombre) {

        CentroCostoFilter filter = new CentroCostoFilter();
        filter.setNombre(nombre);
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);
        List<CentroCosto> centroCostoList = centroCostoService.getByFilter(filter,paginator);
        return centroCostoList.get(0);
    }

    private  TipoGasto getTipoGasto(String nombre) {
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);

        TipoGastoFilter tipoGastoFilter = new TipoGastoFilter();
        tipoGastoFilter.setNombre(nombre);
        List<TipoGasto> tipoGastoList = tipoGastoService.getByFilter(tipoGastoFilter,paginator);
        return tipoGastoList.get(0);
    }

    private  TarjetaCredito getTarjetaCredito(String nombre) {
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);

        TarjetaCreditoFilter tarjetaCreditoFilter  = new TarjetaCreditoFilter();
        tarjetaCreditoFilter.setNombre(nombre);
        System.out.println("nombre tarjeta : " + nombre);
        List<TarjetaCredito> tarjetas = tarjetaCreditoService.getByFilter(tarjetaCreditoFilter,paginator);
        return tarjetas.get(0);

    }



}
