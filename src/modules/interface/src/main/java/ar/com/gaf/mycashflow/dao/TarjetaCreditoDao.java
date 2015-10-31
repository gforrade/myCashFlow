package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.RemovableEntityDao;
import ar.com.gaf.mycashflow.dao.base.SavableEntityDao;
import ar.com.gaf.mycashflow.dao.base.UpdatableEntityDao;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface TarjetaCreditoDao extends SavableEntityDao<TarjetaCredito>, UpdatableEntityDao<TarjetaCredito>, RemovableEntityDao<TarjetaCredito> {

    TarjetaCredito getById(Long tipoGastoId);

    List<TarjetaCredito> getAll();

    List<TarjetaCredito> getByFilter(TarjetaCreditoFilter filter, Paginator paginator);


}