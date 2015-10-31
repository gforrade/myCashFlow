package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.GastoTarjetaCreditoDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.GastoTarjetaCredito;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
@Stateless
public class GastoTarjetaCreditoServiceBean implements GastoTarjetaCreditoService {

    @EJB
    private GastoTarjetaCreditoDao gastoTarjetaCreditoDao;

    @EJB
    private GastoService gastoService;


    @Override
    public List<GastoTarjetaCredito> getAll() {
        return gastoTarjetaCreditoDao.getAll();
    }

    @Override
    public GastoTarjetaCredito getById(Long id) {
        return gastoTarjetaCreditoDao.getById(id);
    }


    @Override
    public void save(GastoTarjetaCredito gastoTarjetaCredito) {

        gastoTarjetaCredito.setImporteTotal(gastoTarjetaCredito.getImporte());

        Calendar cal = Calendar.getInstance();
        cal.setTime(gastoTarjetaCredito.getFechaCompra());
        //se toma el siguiente mes como mes de pago - se computa mes de pago no mes de realizacion de la compra
        cal.add(Calendar.MONTH, 1);

        if (gastoTarjetaCredito.getTarjetaCredito() != null && gastoTarjetaCredito.getDia() > gastoTarjetaCredito.getTarjetaCredito().getCurrentDayCorte()) {
            gastoTarjetaCredito.setMes(gastoTarjetaCredito.getMes()+1);
            cal.add(Calendar.MONTH,1);
        }

        if (gastoTarjetaCredito.getCantidadCuotas() >1 ) {
            for (int i = 1 ; i <= gastoTarjetaCredito.getCantidadCuotas(); i++) {
                GastoTarjetaCredito gastoClone = clone(gastoTarjetaCredito);
                gastoClone.setCuotaNro(i);
                //le sumo al mes que me devuelve Calendar ya que la API de Calendar arranca desde 0 -> Enero
                gastoClone.setMes(cal.get(Calendar.MONTH)+1);
                gastoClone.setAnio(cal.get(Calendar.YEAR));
                gastoClone.setImporte(gastoTarjetaCredito.getImporteTotal() / gastoTarjetaCredito.getCantidadCuotas());
                gastoTarjetaCreditoDao.save(gastoClone, false);
                cal.add(Calendar.MONTH,1);
            }
        } else {
            //se carga como gasto de una sola cuota
            gastoTarjetaCredito.setCuotaNro(1);
            gastoTarjetaCredito.setMes(cal.get(Calendar.MONTH) + 1);
            gastoTarjetaCredito.setAnio(cal.get(Calendar.YEAR));
            gastoTarjetaCreditoDao.save(gastoTarjetaCredito,true);
        }

    }

    @Override
    public GastoTarjetaCredito update(GastoTarjetaCredito gastoTarjetaCredito ) {
        return gastoTarjetaCreditoDao.update(gastoTarjetaCredito, true);
    }

    @Override
    public void remove(GastoTarjetaCredito gastoTarjetaCredito) {
        GastoTarjetaCredito gastoTarjetaCredito1   = gastoTarjetaCreditoDao.getById(gastoTarjetaCredito.getId());
        gastoTarjetaCreditoDao.remove(gastoTarjetaCredito1, true);
    }

    private GastoTarjetaCredito clone(GastoTarjetaCredito gastoTarjetaCredito) {
        GastoTarjetaCredito gastoClone = new GastoTarjetaCredito();
        gastoClone.setCentroCosto(gastoTarjetaCredito.getCentroCosto());
        gastoClone.setTipoGasto(gastoTarjetaCredito.getTipoGasto());
        gastoClone.setTarjetaCredito(gastoTarjetaCredito.getTarjetaCredito());
        gastoClone.setImporteTotal(gastoTarjetaCredito.getImporteTotal());
        gastoClone.setImporte(gastoTarjetaCredito.getImporte());
        gastoClone.setMes(gastoTarjetaCredito.getMes());
        gastoClone.setDescripcion(gastoTarjetaCredito.getDescripcion());
        gastoClone.setMoneda(gastoTarjetaCredito.getMoneda());
        gastoClone.setCantidadCuotas(gastoTarjetaCredito.getCantidadCuotas());
        gastoClone.setFechaCompra(gastoTarjetaCredito.getFechaCompra());
        return  gastoClone;

    }

    public List<GastoTarjetaCredito> findAllGastosTarjetaFromMesAnio(int mes, int anio) {
        return gastoTarjetaCreditoDao.findAllGastosTarjetaFromMesAnio(mes, anio);
    }

    @Override
    public List<ResultadoQuery> findSumGastosAgrupadosCentroCosto(int mes, int anio) {
        return gastoTarjetaCreditoDao.findSumGastosAgrupadosCentroCosto(mes, anio);
    }

    @Override
    public List<ResultadoQuery> findGastosAgrupadosTarjetaAnual(int anio) {
        return gastoTarjetaCreditoDao.findGastosAgrupadosTarjetaAnual(anio);
    }

    @Override
    public List<ResultadoQuery> findGastosByTarjetaMesAnio(String tarjeta, int anio) {
        return gastoTarjetaCreditoDao.findGastosByTarjetaMesAnio(tarjeta, anio);
    }

    @Override
    public List<ResultadoQuery> findGastosAgrupadosCentroCostosAnual(int anio) {
        return gastoTarjetaCreditoDao.findGastosAgrupadosCentroCostosAnual(anio);
    }

    @Override
    public List<ResultadoQuery> findGastosByCentroCostoMesAnio(String centroCosto, int anio) {
        return gastoTarjetaCreditoDao.findGastosByCentroCostoMesAnio(centroCosto, anio);
    }

    @Override
    public List<ResultadoQuery> getTotalTarjetasMesCentroCosto(int mes, int anio) {
        return gastoTarjetaCreditoDao.getTotalTarjetasMesCentroCosto(mes, anio);
    }

}
