package ar.com.gaf.mycashflow.model.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Entity
@DiscriminatorValue("TARJETA")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(
                name="findAllGastosByMesAnio",
                query="SELECT g FROM GastoTarjetaCredito g WHERE g.mes = :mes and g.anio = :anio ORDER BY g.anio,g.mes desc"
),
        @NamedQuery(
        name="findSumGastosAgrupadosCentroCosto",
        query="SELECT g.centroCosto.nombre , SUM(g.importe) FROM GastoTarjetaCredito g  WHERE g.mes = :mes and g.anio = :anio GROUP BY g.centroCosto.nombre ORDER BY  g.centroCosto.nombre"
),
        @NamedQuery(
        name="findGastosAgrupadosTarjetaAnual",
        query="SELECT g.tarjetaCredito.nombre, g.mes, SUM(g.importe) FROM GastoTarjetaCredito g WHERE g.anio = :anio GROUP BY   g.tarjetaCredito.nombre , g.mes ORDER BY g.tarjetaCredito.nombre , g.mes"
),
        @NamedQuery(
        name="findGastosByTarjetaMesAnio",
        query="SELECT g.id , g.descripcion, g.mes, g.importe FROM GastoTarjetaCredito g WHERE  g.tarjetaCredito.nombre = :tarjeta AND g.anio = :anio ORDER BY  g.descripcion, g.mes"
),

        @NamedQuery(
        name="findGastosAgrupadosCentroCostosAnual",
        query="SELECT g.centroCosto.nombre, g.mes, SUM(g.importe) FROM GastoTarjetaCredito g WHERE g.anio = :anio GROUP BY   g.centroCosto.nombre , g.mes ORDER BY g.centroCosto.nombre , g.mes"
),

        @NamedQuery(
        name="findGastosByCentroCostoMesAnio",
        query="SELECT g.id , g.descripcion, g.mes, g.importe FROM GastoTarjetaCredito g WHERE  g.centroCosto.nombre = :centroCosto AND g.anio = :anio ORDER BY  g.descripcion, g.mes"
),
        @NamedQuery(
                name="getTotalTarjetasMesCentroCosto",
                query="SELECT  g.centroCosto.id, SUM(g.importe) FROM GastoTarjetaCredito g WHERE  g.anio = :anio AND g.mes = :mes GROUP BY  g.centroCosto.id , g.mes ORDER BY g.centroCosto.id"
        )


})


public class GastoTarjetaCredito extends Gasto {

    private TarjetaCredito tarjetaCredito;

    private Double importeTotal;
    private int cantidadCuotas = 1;
    private int cuotaNro = 1;

    public GastoTarjetaCredito(TarjetaCredito tarjetaCredito,int cantidadCuotas) {
       this.tarjetaCredito = tarjetaCredito;
        this.cantidadCuotas = cantidadCuotas;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TARJETA_CREDITO_FK", referencedColumnName = "ID_TARJETA_CREDITO")
    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }


    @Column(name="CANTIDAD_CUOTAS",nullable=true)
    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }


    @Column(name="IMPORTE_TOTAL",nullable=true)
    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Column(name="CUOTA_NUMERO",nullable=true)
    public int getCuotaNro() {
        return cuotaNro;
    }

    public void setCuotaNro(int cuotaNro) {
        this.cuotaNro = cuotaNro;
    }

}
