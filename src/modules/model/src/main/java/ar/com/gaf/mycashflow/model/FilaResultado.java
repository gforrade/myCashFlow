package ar.com.gaf.mycashflow.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gforrade on 7/18/15.
 * Copyright (c) 2015, GAF S.A.
 */
public class FilaResultado {

    private Long id;
    private String concepto;
    private Map<Integer,Double> importes= new HashMap<>();
    private Double importeTotal = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getImporteMes1() {
        return getImporte(1);
    }

    public Double getImporteMes2() {
        return getImporte(2);
    }


    public Double getImporteMes3() {
        return getImporte(3);
    }


    public Double getImporteMes4() {
        return getImporte(4);
    }


    public Double getImporteMes5() {
        return getImporte(5);
    }


    public Double getImporteMes6() {
        return getImporte(6);
    }


    public Double getImporteMes7() {
        return getImporte(7);
    }


    public Double getImporteMes8() {
        return getImporte(8);
    }


    public Double getImporteMes9() {
        return getImporte(9);
    }


    public Double getImporteMes10() {
        return getImporte(10);
    }


    public Double getImporteMes11() {
        return getImporte(11);
    }


    public Double getImporteMes12() {
        return getImporte(12);
    }


    public Double getImporteTotalAnual() {
        if (importeTotal == null) {
            importeTotal = 0.0;
            Collection<Double> values = importes.values();
            for (Double value : values) {
                importeTotal = importeTotal + value;
            }
        }
        return importeTotal;
    }

    private Double getImporte(int mes){
        Integer mesInt = new Integer(mes);
        if (importes.containsKey(mesInt)) {
            return importes.get(mesInt);
        } else return new Double(0.0);
    }

    public void setImportes(Map<Integer, Double> importes) {
        this.importes = importes;
    }
}
