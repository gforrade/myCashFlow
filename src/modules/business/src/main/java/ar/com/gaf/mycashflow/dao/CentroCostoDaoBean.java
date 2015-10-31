package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.CentroCostoDao;
import ar.com.gaf.mycashflow.dao.base.AbstractEntityDao;
import ar.com.gaf.mycashflow.dao.base.CustomQuery;
import ar.com.gaf.mycashflow.dao.filters.BaseFilter;
import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Created by gforrade on 7/14/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */

@Stateless
public class CentroCostoDaoBean extends AbstractEntityDao<CentroCosto> implements CentroCostoDao {

    private static final Map<CentroCostoFilter.OrderElement, String> centroCostoFilterOrderByMap;
    static {
        Map<TipoGastoFilter.OrderElement, String> auxMap = new HashMap<TipoGastoFilter.OrderElement, String>();
        auxMap.put(BaseFilter.OrderElement.NOMBRE, "cc.nombre");

        centroCostoFilterOrderByMap = Collections.unmodifiableMap(auxMap);
    }


    @Override
    public CentroCosto getById(Long costoId) {
        return entityManager.find(CentroCosto.class, costoId);
    }

    @Override
    public List<CentroCosto> getAll() {
        return entityManager.createQuery(
                "select cc " +
                        "from CentroCosto cc " +
                        "order by cc.nombre", CentroCosto.class)
                .getResultList();
    }

    @Override
    public List<CentroCosto> getByFilter(CentroCostoFilter filter, Paginator paginator) {

        CustomQuery query = new CustomQuery();

        query.setSelect("cc");
        query.setFrom("CentroCosto cc");

        StringBuilder sbWhere = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        buildWhereAndParams(filter, sbWhere, params);
        query.setWhere(sbWhere.toString());
        query.setParams(params);

        query.setOrderBy(buildOrderBy(filter.getOrderBy(), centroCostoFilterOrderByMap));
        query.setPaginator(paginator);

        return getByQuery(query);
    }

    private void buildWhereAndParams(BaseFilter filter, StringBuilder sbWhere, Map<String, Object> params) {
        // First name
        if (isNotEmpty(filter.getNombre())) {
            sbWhere.append(" and upper(cc.nombre) like :nombre");
            params.put("nombre", "%" + filter.getNombre().toUpperCase() + "%");
        }

        // Last name
        if (isNotEmpty(filter.getDescripcion())) {
            sbWhere.append(" and upper(cc.descripcion) like :descripcion");
            params.put("descripcion", "%" + filter.getDescripcion().toUpperCase() + "%");
        }
    }
}
