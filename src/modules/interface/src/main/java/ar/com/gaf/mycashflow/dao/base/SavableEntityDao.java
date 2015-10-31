package ar.com.gaf.mycashflow.dao.base;

public interface SavableEntityDao<T> {
    void save(T entity);
    void save(T entity, boolean flush);
}
