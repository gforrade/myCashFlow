package ar.com.gaf.mycashflow.model;

/**
 * Created by gforrade on 7/24/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
public class SelectValue {
    Long value;
    String label;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
