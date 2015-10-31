package ar.com.gaf.mycashflow.web.controller;

import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.facade.ForecastFacade;
import ar.com.gaf.mycashflow.model.FilaResultado;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;
import lombok.extern.log4j.Log4j;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class ForecastController {

    @EJB
    ForecastFacade forecastFacade;

    List<FilaResultado> gastosGrupoGastosAnual;
    List<FilaResultado> detalleGastosPorGrupoGastosAnual;
    FilaResultado filaResultadoSeleccionada;
    FilaResultado gastoSeleccionado;
    String backgroundColumnaSeleccionada = "background: greenyellow";

    @PostConstruct
    public void init() {
        gastosGrupoGastosAnual = forecastFacade.findSumGastosAgrupadosGrupoGastos(2015);
    }

    public List<FilaResultado> getGastosGrupoGastosAnual() {
        return gastosGrupoGastosAnual;
    }

    public void setGastosGrupoGastosAnual(List<FilaResultado> gastosGrupoGastosAnual) {
        this.gastosGrupoGastosAnual = gastosGrupoGastosAnual;
    }

    public List<FilaResultado> getDetalleGastosPorGrupoGastosAnual() {
        return detalleGastosPorGrupoGastosAnual;
    }

    public void selectGrupoGasto(FilaResultado filaSeleccionada) {
        filaResultadoSeleccionada = filaSeleccionada;
        detalleGastosPorGrupoGastosAnual = forecastFacade.findDetalleGastosPorGrupoGastos(filaSeleccionada.getConcepto(),2015);
    }


    public void setDetalleGastosPorGrupoGastosAnual(List<FilaResultado> detalleGastosPorGrupoGastosAnual) {
        this.detalleGastosPorGrupoGastosAnual = detalleGastosPorGrupoGastosAnual;
    }

    public String getBackgroundColumnaSeleccionada(int mes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int currentMes = cal.get(Calendar.MONTH)+1;
        if (mes == currentMes) {
            return backgroundColumnaSeleccionada;
        } else return "";
    }

    public void actualizarGastosTarjetas() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.add(Calendar.MONTH, 1);
        int currentMes = cal.get(Calendar.MONTH)+1;
        int currentYear = cal.get(Calendar.YEAR);

        forecastFacade.actualizarGastosTarjetas(currentMes,currentYear);

    }
}
