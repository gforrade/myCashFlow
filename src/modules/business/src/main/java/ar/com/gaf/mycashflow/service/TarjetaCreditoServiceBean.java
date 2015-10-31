package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.TarjetaCreditoDao;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
@Stateless
public class TarjetaCreditoServiceBean implements TarjetaCreditoService {

    @EJB
    private TarjetaCreditoDao tarjetaCreditoDao;

    @Override
    public List<TarjetaCredito> getAll() {
        return tarjetaCreditoDao.getAll();
    }

    @Override
    public TarjetaCredito getById(Long id) {
        return tarjetaCreditoDao.getById(id);
    }


    @Override
    public void save(TarjetaCredito tarjetaCredito) {
        tarjetaCreditoDao.save(tarjetaCredito,true);
    }

    @Override
    public TarjetaCredito update(TarjetaCredito tarjetaCredito ) {
        return tarjetaCreditoDao.update(tarjetaCredito,true);
    }

    @Override
    public void remove(TarjetaCredito tarjetaCredito) {
        TarjetaCredito tarjetaCredito1  = tarjetaCreditoDao.getById(tarjetaCredito.getId());
        tarjetaCreditoDao.remove(tarjetaCredito1,true);
    }

    @Override
    public List<TarjetaCredito> getByFilter(TarjetaCreditoFilter filter, Paginator paginator) {
        return tarjetaCreditoDao.getByFilter(filter,paginator);
    }
}
