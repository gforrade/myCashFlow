package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.TarjetaCreditoDao;
import ar.com.gaf.mycashflow.dao.TipoGastoDao;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
@Stateless
public class TipoGastoServiceBean implements  TipoGastoService {

    @EJB
    private TipoGastoDao tipoGastoDao;

    @Override
    public List<TipoGasto> getAll() {
        return tipoGastoDao.getAll();
    }

    @Override
    public TipoGasto getById(Long id) {
        return tipoGastoDao.getById(id);
    }


    @Override
    public void save(TipoGasto tipoGasto) {
        tipoGastoDao.save(tipoGasto,true);
    }

    @Override
    public TipoGasto update(TipoGasto tipoGasto) {
        return tipoGastoDao.update(tipoGasto,true);
    }

    @Override
    public void remove(TipoGasto tipoGasto) {
        TipoGasto tipoGasto1   = tipoGastoDao.getById(tipoGasto.getId());
        tipoGastoDao.remove(tipoGasto1,true);
    }

    @Override
    public List<TipoGasto> getByFilter(TipoGastoFilter filter, Paginator paginator) {
        return tipoGastoDao.getByFilter(filter,paginator);
    }
}
