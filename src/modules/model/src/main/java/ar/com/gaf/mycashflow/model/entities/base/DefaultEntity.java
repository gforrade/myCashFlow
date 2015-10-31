package ar.com.gaf.mycashflow.model.entities.base;


import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
@MappedSuperclass
public abstract class DefaultEntity<T> extends Entity<T> implements Serializable {
    private static final long serialVersionUID = 2138927728835217684L;
}
