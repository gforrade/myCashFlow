package ar.com.gaf.mycashflow.model.entities.base;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@MappedSuperclass
public abstract class Entity<T> implements Identifiable<T>, Serializable {
    private static final long serialVersionUID = 6969807758778489394L;


    protected T id;

    public T getId(){
        return id;
    };

    public void setId(T id){
        this.id = id;
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        @SuppressWarnings("rawtypes")
        Entity other = Entity.class.cast(obj);
        if (getId() != other.getId())
            return false;
        return true;
    }
}
