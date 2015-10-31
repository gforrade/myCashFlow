package ar.com.gaf.mycashflow.web.controller;

import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.model.FilaResultado;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
import lombok.extern.log4j.Log4j;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gforrade on 7/18/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@ManagedBean
@ViewScoped
public class CashFlowTarjetasController {

    @EJB
    CashFlowFacade cashFlowFacade;

    List<FilaResultado> gastosTarjetasAnual;
    List<FilaResultado> gastosCentroCostoAnual;
    List<FilaResultado> gastoTarjetaCreditoMesAnio;
    List<FilaResultado> gastoCentroCostoMesAnio;
    FilaResultado filaResultadoSeleccionada;
    FilaResultado gastoSeleccionado;
    GastoTarjetaCredito gastoTarjetaCredito;
    String backgroundColumnaSeleccionada = "background: greenyellow";

    @PostConstruct
    public void init() {

        gastosTarjetasAnual = cashFlowFacade.findGastosAgrupadosTarjetaAnual(2015);
        gastosCentroCostoAnual = cashFlowFacade.findGastosAgrupadosCentroCostosAnualList(2015);
    }

    public List<FilaResultado> getGastosTarjetasAnual() {
        return gastosTarjetasAnual;
    }

    public void setGastosTarjetasAnual(List<FilaResultado> gastosTarjetasAnual) {
        this.gastosTarjetasAnual = gastosTarjetasAnual;
    }

    public void seletTarjeta(FilaResultado filaSeleccionada) {
        filaResultadoSeleccionada = filaSeleccionada;
        gastoTarjetaCreditoMesAnio = cashFlowFacade.findGastosByTarjetaMesAnio(filaSeleccionada.getConcepto(),2015);
    }

    public void seletCentroCosto(FilaResultado filaSeleccionada) {
        filaResultadoSeleccionada = filaSeleccionada;
        gastoCentroCostoMesAnio = cashFlowFacade.findGastosAgrupadosCentroCostosAnual(filaSeleccionada.getConcepto(),2015);
    }


    public List<FilaResultado> getGastoTarjetaCreditoMesAnio() {
        return gastoTarjetaCreditoMesAnio;
    }

    public void setGastoTarjetaCreditoMesAnio(List<FilaResultado> gastoTarjetaCreditoMesAnio) {
        this.gastoTarjetaCreditoMesAnio = gastoTarjetaCreditoMesAnio;
    }

    public FilaResultado getSelectedFilaResultado() {
        return this.filaResultadoSeleccionada;
    }

    public void setSelectedFilaResultado(FilaResultado filaResultado) {
         this.filaResultadoSeleccionada = filaResultado;
    }

    public FilaResultado getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(FilaResultado gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public void onRowSelect(SelectEvent event) {
        FilaResultado filaSeleccionada = (FilaResultado) event.getObject();
        gastoTarjetaCredito = cashFlowFacade.getById(filaSeleccionada.getId());
    }

    public void onRowUnselect(UnselectEvent event) {
        System.out.println("onRowUnselect");
    }

    public GastoTarjetaCredito getGastoTarjetaCredito() {
        return gastoTarjetaCredito;
    }

    public void setGastoTarjetaCredito(GastoTarjetaCredito gastoTarjetaCredito) {
        this.gastoTarjetaCredito = gastoTarjetaCredito;
    }

    public List<FilaResultado> getGastosCentroCostoAnual() {
        return gastosCentroCostoAnual;
    }

    public void setGastosCentroCostoAnual(List<FilaResultado> gastosCentroCostoAnual) {
        this.gastosCentroCostoAnual = gastosCentroCostoAnual;
    }

    public List<FilaResultado> getGastoCentroCostoMesAnio() {
        return gastoCentroCostoMesAnio;
    }

    public void setGastoCentroCostoMesAnio(List<FilaResultado> gastoCentroCostoMesAnio) {
        this.gastoCentroCostoMesAnio = gastoCentroCostoMesAnio;
    }

    public String getBackgroundColumnaSeleccionada(int mes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int currentMes = cal.get(Calendar.MONTH)+1;
        if (mes == currentMes) {
            return backgroundColumnaSeleccionada;
        } else return "";
    }
}
