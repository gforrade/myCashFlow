package ar.com.gaf.mycashflow.dao.base;

public interface UpdatableEntityDao<T> {
    T update(T entity);
    T update(T entity, boolean flush);
}
