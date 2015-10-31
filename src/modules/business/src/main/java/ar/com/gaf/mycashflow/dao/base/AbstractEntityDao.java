package ar.com.gaf.mycashflow.dao.base;

import ar.com.gaf.mycashflow.dao.filters.commons.OrderDirection;
import ar.com.gaf.mycashflow.dao.filters.commons.OrderExpression;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
public abstract class AbstractEntityDao<T> {

    private static final long serialVersionUID = -4993169756952347374L;

    @PersistenceContext(unitName = "em")
    protected EntityManager entityManager;

//    @Inject Principal principal;

    @SuppressWarnings("unchecked")
    protected List<T> getByQuery(CustomQuery query) {
        System.out.println("### getByQuery - Inicio");

        StringBuilder sbQuery = new StringBuilder();
        if (query.getSelect() != null)
            sbQuery.append("select distinct ").append(query.getSelect());
        sbQuery.append(" from ");
        if (query.getEagerLoadFrom() != null)
            sbQuery.append(query.getEagerLoadFrom());
        else
            sbQuery.append(query.getFrom());
        if (query.getWhere() != null)
            sbQuery.append(" where 1 = 1 ").append(query.getWhere());
        if (query.getOrderBy() != null)
            sbQuery.append(" order by ").append(query.getOrderBy());

        Query q = entityManager.createQuery(sbQuery.toString());

        if (query.getPaginator() != null) {
            StringBuilder sbQueryCount = new StringBuilder();
            if (query.getSelect() != null && query.getEagerLoadFrom() != null)
                sbQueryCount.append("select count(distinct ").append(query.getSelect()).append(") ");
            else
                sbQueryCount.append("select count(" + query.getSelect() + ") ");
            sbQueryCount.append(" from ").append(query.getFrom());
            if (query.getWhere() != null)
                sbQueryCount.append(" where 1 = 1 ").append(query.getWhere());

            TypedQuery<Long> qc = entityManager.createQuery(sbQueryCount.toString(), Long.class);

            if (query.getParams() != null) {
                for (Map.Entry<String, Object> param : query.getParams().entrySet()) {
                    qc.setParameter(param.getKey(), param.getValue());
                    q.setParameter(param.getKey(), param.getValue());
                }
            }

            Long rowCount = qc.getSingleResult();
            query.getPaginator().setRowCount(rowCount.intValue());

            if (query.getPaginator().isOnlyCount()) {
                // No es la mejor salida de método... pero funciona
                return null;
            }

            q.setFirstResult(query.getPaginator().getFirst());
            q.setMaxResults(query.getPaginator().getPageSize());
        }
        else if (query.getParams() != null) {
            for (Map.Entry<String, Object> param : query.getParams().entrySet()) {
                q.setParameter(param.getKey(), param.getValue());
            }
        }

        System.out.println("### getByQuery - Fin");

        //noinspection unchecked
        return q.getResultList();
    }

    protected <E extends Enum<E>> String buildOrderBy(List<OrderExpression<E>> orderBy, Map<E, String> orderByMap) {
        if (orderBy == null)
            return null;
        StringBuilder sbOrderBy = null;
        for (OrderExpression<E> orderExpression : orderBy) {
            String orderElementValue = orderByMap.get(orderExpression.getElement());
            if (orderElementValue == null) {
                // TODO: Hacer excepción
                System.out.println("ERROR: Mapped value not found for enum OrderElement \"" + orderElementValue + "\".");
                sbOrderBy = null;
                break;
            }
            if (sbOrderBy == null)
                sbOrderBy = new StringBuilder(orderElementValue);
            else
                sbOrderBy.append(", ").append(orderElementValue);
            if (orderExpression.getDirection() == OrderDirection.DESC)
                sbOrderBy.append(" desc");
        }
        if (sbOrderBy == null)
            return null;
        return sbOrderBy.toString();
    }

    public void save(T entity) {
        save(entity, true);
    }

    public void save(T entity, boolean flush) {

        entityManager.persist(entity);
        if (flush)
            entityManager.flush();
    }

    public T update(T entity) {
        return update(entity, true);
    }

    public T update(T entity, boolean flush) {

        T entityResult = entityManager.merge(entity);
        if (flush)
            entityManager.flush();
        return entityResult;
    }

    public void remove(T entity) {
        remove(entity, true);
    }

    public void remove(T entity, boolean flush) {
        entityManager.remove(entity);
        if (flush)
            entityManager.flush();
    }
}
