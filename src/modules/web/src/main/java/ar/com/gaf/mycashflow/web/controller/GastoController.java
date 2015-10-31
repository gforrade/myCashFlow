package ar.com.gaf.mycashflow.web.controller;

import ar.com.gaf.mycashflow.facade.GastoFacade;
import ar.com.gaf.mycashflow.model.*;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.GrupoGasto;
import ar.com.gaf.mycashflow.model.entities.Moneda;
import lombok.extern.log4j.Log4j;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.*;

/**
 * Created by gforrade on 7/18/15.
 * Copyright (c) 2015, GAF S.A.
 */
@Log4j
@ManagedBean
@ViewScoped
public class GastoController {

    @EJB
    GastoFacade gastoFacade;

    List<SelectValue> centroCostos;
    List<SelectValue> tarjetasCredito;
    List<SelectValue> grupoGastos;
    List<SelectValue> subCategoriasGasto;
    List<SelectValue> meses;

    private Long centroCostoId;
    private Long categoriaGastoId;
    private Long subCategoriaGastoId;
    private Long tarjetaCreditoId;
    private Long tipoGastoId;
    private String concepto;

    private Date fechaCompra;
    private Date fechaInicio;
    private Double importe;
    private int cantidadCuotas;
    private int cantidadMeses;

    private Map<String,List<SelectValue>> mapaSubcategorias;


    @PostConstruct
    public void init() {
        centroCostos = gastoFacade.getCentrosCosto();
        tarjetasCredito = gastoFacade.getTarjetasCredito();
        grupoGastos = gastoFacade.getGruposGasto();
        meses = buildMeses();
        fechaCompra = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaCompra);
        //seteamos calendar en el 1 de Enero del mes en curso
        cal.set(cal.get(Calendar.YEAR), 0, 1);
        fechaInicio = cal.getTime();
        mapaSubcategorias = builMapSubcategorias();

    }

    public Long getCentroCostoId() {
        return centroCostoId;
    }

    public void setCentroCostoId(Long centroCostoId) {
        this.centroCostoId = centroCostoId;
    }

    public List<SelectValue> getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(List<SelectValue> centroCostos) {
        this.centroCostos = centroCostos;
    }

    public List<SelectValue> getTarjetasCredito() {
        return tarjetasCredito;
    }

    public void setTarjetasCredito(List<SelectValue> tarjetasCredito) {
        this.tarjetasCredito = tarjetasCredito;
    }

    public Long getCategoriaGastoId() {
        return categoriaGastoId;
    }

    public void setCategoriaGastoId(Long categoriaGastoId) {
        this.categoriaGastoId = categoriaGastoId;
    }

    public List<SelectValue> getGrupoGastos() {
        return grupoGastos;
    }


    public void setTarjetaCredito(Long tarjetaCredito) {
        this.tarjetaCreditoId = tarjetaCredito;
    }

    public Date getFechaCompra() {
        fechaCompra = new Date(System.currentTimeMillis());
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public List<SelectValue> getSubCategoriasGasto() {
      return subCategoriasGasto;
    }

    public void setSubCategoriasGasto(List<SelectValue> subCategoriasGasto) {
        this.subCategoriasGasto = subCategoriasGasto;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public Long getSubCategoriaGastoId() {
        return subCategoriaGastoId;
    }

    public void setSubCategoriaGastoId(Long subCategoriaGastoId) {
        this.subCategoriaGastoId = subCategoriaGastoId;
    }

    public Long getTarjetaCreditoId() {
        return tarjetaCreditoId;
    }

    public void setTarjetaCreditoId(Long tarjetaCreditoId) {
        this.tarjetaCreditoId = tarjetaCreditoId;
    }

    public Long getTipoGastoId() {
        return tipoGastoId;
    }

    public void setTipoGastoId(Long tipoGastoId) {
        this.tipoGastoId = tipoGastoId;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public int getCantidadMeses() {
        return cantidadMeses;
    }

    public void setCantidadMeses(int cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    public List<SelectValue> getMeses() {
        return meses;
    }

    public void setMeses(List<SelectValue> meses) {
        this.meses = meses;
    }

    public void onSelectCategoria(AjaxBehaviorEvent event) {
        System.out.println("categoria " + categoriaGastoId);
        SelectOneMenu selectMenu=((SelectOneMenu)event.getSource());
        System.out.println("selectMenu.getValue()-> "+selectMenu.getValue());
        subCategoriasGasto = mapaSubcategorias.get(GrupoGasto.getName(categoriaGastoId));
    }
    private List<SelectValue> buildMeses()
    {
        List<SelectValue> list = new ArrayList<>();
        SelectValue selectValue = null;

        selectValue = new SelectValue();
        selectValue.setLabel("1");
        selectValue.setValue((long) 1);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("2");
        selectValue.setValue((long) 2);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("3");
        selectValue.setValue((long) 3);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("4");
        selectValue.setValue((long) 4);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("5");
        selectValue.setValue((long) 5);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("6");
        selectValue.setValue((long) 6);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("7");
        selectValue.setValue((long) 7);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("8");
        selectValue.setValue((long) 8);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("9");
        selectValue.setValue((long) 9);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("10");
        selectValue.setValue((long) 10);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("11");
        selectValue.setValue((long) 11);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("12");
        selectValue.setValue((long) 12);
        list.add(selectValue);

        selectValue = new SelectValue();
        selectValue.setLabel("18");
        selectValue.setValue((long) 18);
        list.add(selectValue);

        return list;

    }



    public void agregarGasto() {
        //por default cargo el centro de costo como familiar
        gastoFacade.addGasto(centroCostoId, tipoGastoId, importe, fechaInicio, null, Moneda.PESOS, cantidadMeses);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Concepto Cargado Exitosamente!"));
    }

    public void agregarGastoTarjeta() {
        gastoFacade.addGastoTarjetaCredito(tarjetaCreditoId,centroCostoId, importe, fechaCompra, concepto, Moneda.PESOS, cantidadCuotas);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Gasto Cargado Exitosamente!"));

    }

    private  <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }

    private Map<String,List<SelectValue>> builMapSubcategorias() {
        Map<String,List<SelectValue>> mapa = new HashMap<>();
        for (GrupoGasto grupoGasto : GrupoGasto.values()) {
            List<SelectValue> tiposGastos = gastoFacade.getTiposGasto(grupoGasto.name());
            mapa.put(grupoGasto.name(),tiposGastos);
        }
        return mapa;
    }


}
