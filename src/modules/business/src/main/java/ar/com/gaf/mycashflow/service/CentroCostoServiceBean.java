package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.CentroCostoDao;
import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
@Stateless
public class CentroCostoServiceBean implements  CentroCostoService {

    @EJB
    private CentroCostoDao centroCostoDao;

    @Override
    public List<CentroCosto> getAll() {
        return centroCostoDao.getAll();
    }

    @Override
    public CentroCosto getById(Long id) {
        return centroCostoDao.getById(id);
    }


    @Override
    public void save(CentroCosto centroCosto) {
        centroCostoDao.save(centroCosto,true);
    }

    @Override
    public CentroCosto update(CentroCosto centroCosto) {
        return centroCostoDao.update(centroCosto,true);
    }

    @Override
    public void remove(CentroCosto centroCosto) {
        CentroCosto centroCosto1 = centroCostoDao.getById(centroCosto.getId());
        centroCostoDao.remove(centroCosto1,true);
    }

    @Override
    public List<CentroCosto> getByFilter(CentroCostoFilter filter, Paginator paginator) {
        return centroCostoDao.getByFilter(filter,paginator);
    }
}
