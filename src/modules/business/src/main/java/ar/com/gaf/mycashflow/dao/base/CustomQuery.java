package ar.com.gaf.mycashflow.dao.base;

import java.util.Map;

import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;

public class CustomQuery {
    private String select;
    private String from;
    private String eagerLoadFrom;
    private String where;
    private Map<String, Object> params;
    private String orderBy;
    private Paginator paginator;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEagerLoadFrom() {
        return eagerLoadFrom;
    }

    public void setEagerLoadFrom(String eagerLoadFrom) {
        this.eagerLoadFrom = eagerLoadFrom;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
