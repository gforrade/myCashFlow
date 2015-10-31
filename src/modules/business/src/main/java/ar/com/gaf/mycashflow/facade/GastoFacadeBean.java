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
import java.util.*;

/**
 * Created by gforrade on 7/16/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@Stateless
public class GastoFacadeBean implements GastoFacade {

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
    public List<SelectValue> getCentrosCosto() {
        List<CentroCosto> centrosCosto = centroCostoService.getAll();
        List<SelectValue> list = new ArrayList<>();

        for (CentroCosto centroCosto : centrosCosto) {
            SelectValue selectValue = null;
            selectValue = new SelectValue();
            selectValue.setValue(centroCosto.getId());
            selectValue.setLabel(centroCosto.getNombre());
            list.add(selectValue);
        }
        return list;
    }

    @Override
    public List<SelectValue> getTarjetasCredito()
    {
        List<TarjetaCredito> tarjetaCreditoList = tarjetaCreditoService.getAll();
        List<SelectValue> list = new ArrayList<>();
        for (TarjetaCredito tarjetaCredito : tarjetaCreditoList) {
            SelectValue selectValue = null;
            selectValue = new SelectValue();
            selectValue.setValue(tarjetaCredito.getId());
            selectValue.setLabel(tarjetaCredito.getNombre());
            list.add(selectValue);
        }
        return list;
    }

    @Override
    public List<SelectValue> getTiposGasto() {
        List<TipoGasto> tipoGastoList = tipoGastoService.getAll();
        List<SelectValue> list = new ArrayList<>();
        for (TipoGasto tipoGasto : tipoGastoList) {
            SelectValue selectValue = null;
            selectValue = new SelectValue();
            selectValue.setValue(tipoGasto.getId());
            selectValue.setLabel(tipoGasto.getNombre());
            list.add(selectValue);
        }
        return list;
    }

    @Override
    public List<SelectValue> getGruposGasto() {
        List<SelectValue> list = new ArrayList<>();
        GrupoGasto[] grupoGastos = GrupoGasto.values();
        for (GrupoGasto grupoGasto : grupoGastos) {
            SelectValue selectValue = null;
            selectValue = new SelectValue();
            selectValue.setValue(GrupoGasto.getId(grupoGasto));
            selectValue.setLabel(grupoGasto.name());
            list.add(selectValue);
        }

        return list;
    }

    @Override
    public List<SelectValue> getTiposGasto(String grupoGasto) {
        List<SelectValue> selectValueList = new ArrayList<>();
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(100);

        TipoGastoFilter tipoGastoFilter = new TipoGastoFilter();
        tipoGastoFilter.setGrupoGasto(grupoGasto);
        List<TipoGasto> tipoGastoList = tipoGastoService.getByFilter(tipoGastoFilter,paginator);
        SelectValue selectValue = null;
        for (TipoGasto tipoGasto : tipoGastoList) {
            selectValue = new SelectValue();
            selectValue.setValue(tipoGasto.getId());
            selectValue.setLabel(tipoGasto.getNombre());
            selectValueList.add(selectValue);
        }
        return selectValueList;
    }

    @Override
    public List<SelectValue> getMonedas() {
        return null;
    }

    @Override
    public void addGasto(CentroCostoEnum centroCostoEnum,TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
            createGasto(getCentroCosto(centroCostoEnum.name()), getTipoGasto(tipoGasto.name()), importe, fechaCompra, descripcion, moneda, cantidadMeses);
    }

    @Override
    public void addGasto(String centroCosto, String tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        createGasto(getCentroCosto(centroCosto), getTipoGasto(tipoGasto), importe, fechaCompra, descripcion, moneda, cantidadMeses);
    }

    @Override
    public void addGasto(Long centroCostoId, Long tipoGastoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        CentroCosto centroCosto = centroCostoService.getById(centroCostoId);
        TipoGasto tipoGasto = tipoGastoService.getById(tipoGastoId);
        //en caso de venir nulo el campo descripcion, seteamos el nombre del tipo de gasto como descripcion
        if (descripcion == null) {
            descripcion = tipoGasto.getNombre();
        }
        createGasto(centroCosto,tipoGasto,importe,fechaCompra,descripcion,moneda,cantidadMeses);
    }

    @Override
    public void addGastoTarjetaCredito(Long tarjetaCreditoId, Long centroCostoId, Long tipoGastoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        CentroCosto centroCosto = centroCostoService.getById(centroCostoId);
        TipoGasto tipoGasto = tipoGastoService.getById(tipoGastoId);
        TarjetaCredito tarjetaCredito = tarjetaCreditoService.getById(tarjetaCreditoId);
        Gasto gasto = new Gasto(centroCosto, tipoGasto, importe,  fechaCompra, descripcion, Moneda.PESOS);
        createGastoTarjeta(gasto, tarjetaCredito, cantidadMeses);
    }

    @Override
    public void addGastoTarjetaCredito(TarjetaCreditoEnum tarjetaCreditoEnum, CentroCostoEnum centroCostoEnum, TipoGastoEnum tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        Gasto gasto = new Gasto(getCentroCosto(centroCostoEnum.name()), getTipoGasto(tipoGasto.name()), importe,  fechaCompra, descripcion, Moneda.PESOS);
        createGastoTarjeta(gasto, getTarjetaCredito(tarjetaCreditoEnum.name()), cantidadMeses);
    }

    @Override
    public void addGastoTarjetaCredito(String tarjetaCredito, String centroCosto, String tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        Gasto gasto = new Gasto(getCentroCosto(centroCosto), getTipoGasto(tipoGasto), importe,  fechaCompra, descripcion, Moneda.PESOS);
        createGastoTarjeta(gasto, getTarjetaCredito(tarjetaCredito), cantidadMeses);
    }

    @Override
    public void addGastoTarjetaCredito(Long tarjetaCreditoId, Long centroCostoId, Double importe, Date fechaCompra, String descripcion, Moneda moneda, int cantidadMeses) {
        CentroCosto centroCosto = centroCostoService.getById(centroCostoId);
        TarjetaCredito tarjetaCredito = tarjetaCreditoService.getById(tarjetaCreditoId);
        Gasto gasto = new Gasto(centroCosto, importe,  fechaCompra, descripcion, Moneda.PESOS);
        createGastoTarjeta(gasto, tarjetaCredito, cantidadMeses);
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
