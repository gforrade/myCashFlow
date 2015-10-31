package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;


import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface CentroCostoDao extends SavableEntityDao<CentroCosto>, UpdatableEntityDao<CentroCosto>, RemovableEntityDao<CentroCosto> {
    CentroCosto getById(Long costoId);

    List<CentroCosto> getAll();

    List<CentroCosto> getByFilter(CentroCostoFilter filter, Paginator paginator);



}