package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.base.AbstractEntityDao;
import ar.com.gaf.mycashflow.dao.base.CustomQuery;
import ar.com.gaf.mycashflow.dao.filters.BaseFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
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
public class TipoGastoDaoBean extends AbstractEntityDao<TipoGasto> implements TipoGastoDao {
    @Override
    public TipoGasto getById(Long costoId) {
        return entityManager.find(TipoGasto.class, costoId);
    }
    private static final Map<TipoGastoFilter.OrderElement, String> tipoGastoFilterOrderByMap;
    static {
        Map<TipoGastoFilter.OrderElement, String> auxMap = new HashMap<TipoGastoFilter.OrderElement, String>();
        auxMap.put(BaseFilter.OrderElement.NOMBRE, "tg.nombre");

        tipoGastoFilterOrderByMap = Collections.unmodifiableMap(auxMap);
    }

    @Override
    public List<TipoGasto> getAll() {
        return entityManager.createQuery(
                "select tg " +
                        "from TipoGasto tg " +
                        "order by tg.nombre", TipoGasto.class)
                .getResultList();
    }

    @Override
    public List<TipoGasto> getByFilter(TipoGastoFilter filter, Paginator paginator) {

        CustomQuery query = new CustomQuery();

        query.setSelect("tg");
        query.setFrom("TipoGasto tg");

        StringBuilder sbWhere = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        buildWhereAndParams(filter, sbWhere, params);
        query.setWhere(sbWhere.toString());
        query.setParams(params);

        query.setOrderBy(buildOrderBy(filter.getOrderBy(), tipoGastoFilterOrderByMap));
        query.setPaginator(paginator);

        return getByQuery(query);
    }

    private void buildWhereAndParams(BaseFilter filter, StringBuilder sbWhere, Map<String, Object> params) {
        // First name
        if (isNotEmpty(filter.getNombre())) {
            sbWhere.append(" and upper(tg.nombre) like :nombre");
            params.put("nombre", "%" + filter.getNombre().toUpperCase() + "%");
        }

        // Last name
        if (isNotEmpty(filter.getDescripcion())) {
            sbWhere.append(" and upper(tg.descripcion) like :descripcion");
            params.put("descripcion", "%" + filter.getDescripcion().toUpperCase() + "%");
        }

        if (isNotEmpty(filter.getGrupoGasto())) {
            sbWhere.append(" and upper(tg.grupoGasto) like :grupoGasto");
            params.put("grupoGasto", "%" + filter.getGrupoGasto().toUpperCase() + "%");
        }

    }

}


