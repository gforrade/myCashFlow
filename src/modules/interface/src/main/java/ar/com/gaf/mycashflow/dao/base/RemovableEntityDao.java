package ar.com.gaf.mycashflow.dao.base;

public interface RemovableEntityDao<T> {
    void remove(T entity);
    void remove(T entity, boolean flush);
}
