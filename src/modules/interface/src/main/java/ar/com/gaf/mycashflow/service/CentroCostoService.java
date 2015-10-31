package ar.com.gaf.mycashflow.service;

import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Local
public interface CentroCostoService {
    List<CentroCosto> getAll();
    CentroCosto getById(Long id);

    void save(CentroCosto centroCosto);
    CentroCosto update(CentroCosto centroCosto);
    void remove(CentroCosto centroCosto);
    public List<CentroCosto> getByFilter(CentroCostoFilter filter, Paginator paginator) ;

}
