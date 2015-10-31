package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface TipoGastoDao extends SavableEntityDao<TipoGasto>, UpdatableEntityDao<TipoGasto>, RemovableEntityDao<TipoGasto> {

    TipoGasto getById(Long tipoGastoId);

    List<TipoGasto> getAll();

    List<TipoGasto> getByFilter(TipoGastoFilter filter, Paginator paginator);
}