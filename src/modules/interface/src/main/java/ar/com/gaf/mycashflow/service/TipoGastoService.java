package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface TipoGastoService {
    List<TipoGasto> getAll();
    TipoGasto getById(Long id);

    void save(TipoGasto tipoGasto);
    TipoGasto update(TipoGasto tipoGasto);
    void remove(TipoGasto tipoGasto);

    public List<TipoGasto> getByFilter(TipoGastoFilter filter, Paginator paginator);
}
