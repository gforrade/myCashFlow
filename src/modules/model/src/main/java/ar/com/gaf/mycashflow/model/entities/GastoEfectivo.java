package ar.com.gaf.mycashflow.model.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;

/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */

@Entity
@DiscriminatorValue("EFECTIVO")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(
                name="findSumGastosAgrupadosGrupoGastos",
                query="SELECT g.tipoGasto.grupoGasto , g.mes, SUM(g.importe) FROM GastoEfectivo g  WHERE g.anio = :anio GROUP BY   g.tipoGasto.grupoGasto , g.mes ORDER BY g.tipoGasto.grupoGasto , g.mes"
        ),
        @NamedQuery(
        name="findImporteTotalMes",
        query="SELECT g.mes, SUM(g.importe) FROM GastoEfectivo g  WHERE g.anio = :anio GROUP BY  g.mes ORDER BY  g.mes asc"
),
        @NamedQuery(
                name="findDetalleGastosPorGrupoGastos",
                query="SELECT g.tipoGasto.nombre , g.mes, SUM(g.importe) FROM GastoEfectivo g  WHERE g.anio = :anio AND g.tipoGasto.grupoGasto = :grupoGasto GROUP BY  g.tipoGasto.nombre ,  g.mes ORDER BY  g.tipoGasto.nombre"
        ),
        @NamedQuery(
                name = "findByCentroCostoIdMesAnio",
                query = "SELECT g FROM GastoEfectivo g WHERE g.centroCosto.id = :idCentroCosto and g.mes = :mes and g.anio = :anio"
        )

})

public class GastoEfectivo extends Gasto {

    public GastoEfectivo(CentroCosto centroCosto, TipoGasto tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda) {
        super(centroCosto, tipoGasto, importe, fechaCompra, descripcion, moneda);
    }
}
