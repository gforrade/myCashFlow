package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface TarjetaCreditoService {
    List<TarjetaCredito> getAll();
    TarjetaCredito getById(Long id);

    void save(TarjetaCredito tarjetaCredito);
    TarjetaCredito update(TarjetaCredito tarjetaCredito );
    void remove(TarjetaCredito tarjetaCredito);

    public List<TarjetaCredito> getByFilter(TarjetaCreditoFilter filter, Paginator paginator);
}
