package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.AbstractEntityDao;
import ar.com.gaf.mycashflow.dao.base.CustomQuery;
import ar.com.gaf.mycashflow.dao.filters.BaseFilter;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
import ar.com.gaf.mycashflow.model.entities.TipoGasto;

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
public class TarjetaCreditoDaoBean extends AbstractEntityDao<TarjetaCredito> implements TarjetaCreditoDao {

    private static final Map<TarjetaCreditoFilter.OrderElement, String> tarjetaCreditoFilterOrderByMap;
    static {
        Map<TipoGastoFilter.OrderElement, String> auxMap = new HashMap<TarjetaCreditoFilter.OrderElement, String>();
        auxMap.put(BaseFilter.OrderElement.NOMBRE, "tc.nombre");

        tarjetaCreditoFilterOrderByMap = Collections.unmodifiableMap(auxMap);
    }

    @Override
    public TarjetaCredito getById(Long id) {
        return entityManager.find(TarjetaCredito.class, id);
    }

    @Override
    public List<TarjetaCredito> getAll() {
        return entityManager.createQuery(
                "select tc " +
                        "from TarjetaCredito tc " +
                        "order by tc.nombre", TarjetaCredito.class)
                .getResultList();
    }


    @Override
    public List<TarjetaCredito> getByFilter(TarjetaCreditoFilter filter, Paginator paginator) {

        CustomQuery query = new CustomQuery();

        query.setSelect("tc");
        query.setFrom("TarjetaCredito tc");

        StringBuilder sbWhere = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        buildWhereAndParams(filter, sbWhere, params);
        query.setWhere(sbWhere.toString());
        query.setParams(params);

        query.setOrderBy(buildOrderBy(filter.getOrderBy(), tarjetaCreditoFilterOrderByMap));
        query.setPaginator(paginator);

        return getByQuery(query);
    }

    private void buildWhereAndParams(BaseFilter filter, StringBuilder sbWhere, Map<String, Object> params) {
        // First name
        if (isNotEmpty(filter.getNombre())) {
            sbWhere.append(" and upper(tc.nombre) like :nombre");
            params.put("nombre", "%" + filter.getNombre().toUpperCase() + "%");
        }

        // Last name
        if (isNotEmpty(filter.getDescripcion())) {
            sbWhere.append(" and upper(tc.descripcion) like :descripcion");
            params.put("descripcion", "%" + filter.getDescripcion().toUpperCase() + "%");
        }
    }
}
