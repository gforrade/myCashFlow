package ar.com.gaf.mycashflow.model.entities.base;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
public interface Identifiable<T> {
    T getId();
    void setId(T id);
}
