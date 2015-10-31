package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.GastoDao;
import ar.com.gaf.mycashflow.dao.TipoGastoDao;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.entities.Gasto;
import ar.com.gaf.mycashflow.model.entities.GastoEfectivo;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
@Stateless
public class GastoServiceBean implements  GastoService {

    @EJB
    private GastoDao gastoDao;

    @Override
    public List<GastoEfectivo> getAll() {
        return gastoDao.getAll();
    }

    @Override
    public GastoEfectivo getById(Long id) {
        return gastoDao.getById(id);
    }


    @Override
    public void save(GastoEfectivo gasto, int meses ) {
        System.out.println("fechaCompra-save "+ gasto.getFechaCompra());
        Calendar cal = Calendar.getInstance();
        cal.setTime(gasto.getFechaCompra());

        for (int i = 1 ; i <= meses ; i++) {
            GastoEfectivo gastoClone = clone(gasto);
            gastoClone.setFechaCompra(cal.getTime());
            gastoDao.save(gastoClone, false);
            cal.add(Calendar.MONTH, 1);
        }
    }

    @Override
    public void save(GastoEfectivo gasto) {
        gastoDao.save(gasto,true);
    }


    @Override
    public GastoEfectivo update(GastoEfectivo gasto) {
        return gastoDao.update(gasto,true);
    }

    @Override
    public void remove(GastoEfectivo gasto) {
        GastoEfectivo gasto1   = gastoDao.getById(gasto.getId());
        gastoDao.remove(gasto1,true);
    }

    @Override
    public List<ResultadoQuery> findSumGastosAgrupadosGrupoGastos( int anio) {
        return gastoDao.findSumGastosAgrupadosGrupoGastos(anio);
    }

    @Override
    public List<ResultadoQuery> findImporteTotalMes(int anio) {
        return gastoDao.findImporteTotalMes(anio);
    }

    @Override
    public List<ResultadoQuery> findDetalleGastosPorGrupoGastos(String grupoGasto, int anio) {
        return gastoDao.findDetalleGastosPorGrupoGastos(grupoGasto,anio);
    }

    private GastoEfectivo clone(Gasto gasto) {
        GastoEfectivo gastoClone = new GastoEfectivo();
        gastoClone.setCentroCosto(gasto.getCentroCosto());
        gastoClone.setTipoGasto(gasto.getTipoGasto());
        gastoClone.setImporte(gasto.getImporte());
        gastoClone.setMes(gasto.getMes());
        gastoClone.setDescripcion(gasto.getDescripcion());
        gastoClone.setMoneda(gasto.getMoneda());
        return  gastoClone;

    }

    @Override
    public void updateGastoTarjetaCentroCostoMesAnio(Long idCentroCosto, Double importe, int mes, int anio) {
        GastoEfectivo gastoEfectivo = gastoDao.findByCentroCostoIdMesAnio(idCentroCosto,mes,anio);
        gastoEfectivo.setImporte(importe);
        gastoDao.update(gastoEfectivo,true);
    }
}
