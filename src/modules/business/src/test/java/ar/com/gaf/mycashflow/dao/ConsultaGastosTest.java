package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.*;
import ar.com.gaf.mycashflow.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ejb.embeddable.EJBContainer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Test(groups = "consultaGastos")
public class ConsultaGastosTest {
	private static final Log log = LogFactory.getLog(ConsultaGastosTest.class);
	
	private static EJBContainer ejbContainer;

    private static CentroCostoService centroCostoService;
    private static TarjetaCreditoService tarjetaCreditoService;
    private static GastoService gastoService;
    private static TipoGastoService tipoGastoService;
    private static GastoTarjetaCreditoService gastoTarjetaCreditoService;
    private static CashFlowFacade cashFlowFacade;

    private Long centroCostoId;

    @BeforeClass
    public static void setUpClass() throws Exception {
    	//ejbContainer = EJBContainer.createEJBContainer();
    	log.info("ejbContainer: " + ejbContainer);
    	// TODO: instanciar correctamente un contenedor... arquillian, glassfish-embedded, weblogic

    	centroCostoService = (CentroCostoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/CentroCostoServiceBean");
        tarjetaCreditoService = (TarjetaCreditoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/TarjetaCreditoServiceBean");
        gastoService = (GastoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/GastoServiceBean");
        tipoGastoService = (TipoGastoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/TipoGastoServiceBean");
        gastoTarjetaCreditoService = (GastoTarjetaCreditoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/GastoTarjetaCreditoServiceBean");
        cashFlowFacade = (CashFlowFacade)ContainerManager.getInstance().getContext().lookup("java:global/classes/CashFlowFacadeBean");




    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    	if (ejbContainer != null) {
    		ejbContainer.close();
    	}
    }







    @Test
    public void findSumGastosAgrupadosCentroCosto() {
        List<ResultadoQuery> lista = gastoService.findSumGastosAgrupadosGrupoGastos(2015);
        for (ResultadoQuery resultadoQuery : lista) {
            System.out.println(resultadoQuery.getNombre()+" : " + resultadoQuery.getMes()+" : " +resultadoQuery.getImporte());
        }

    }
    @Test
    public void cargarGastosJulio() {

    }

/*
    @Test(dependsOnMethods = "createGastosTarjeta")
    public void findGastos() {
        List<GastoEfectivo> gastos = gastoService.getAll();
        for (GastoEfectivo gasto : gastos) {
            System.out.println(gasto);
        }

        List<GastoTarjetaCredito> gastoTarjetaCreditos= gastoTarjetaCreditoService.getAll();
        for (GastoTarjetaCredito gastoTarjetaCredito : gastoTarjetaCreditos) {
            System.out.println(gastoTarjetaCredito);
        }
    }

    @Test(dependsOnMethods = "findGastos")
    public void findAllGastosTarjetaFromMesAnio() {
        List<GastoTarjetaCredito> lista = gastoTarjetaCreditoService.findAllGastosTarjetaFromMesAnio(7, 2015);
        for (GastoTarjetaCredito gastoTarjetaCredito : lista) {
            System.out.println(gastoTarjetaCredito);
        }
    }

    @Test(dependsOnMethods = "findAllGastosTarjetaFromMesAnio")
    public void findSumGastosAgrupadosCentroCosto() {
        List<ResultadoQuery> lista = gastoTarjetaCreditoService.findSumGastosAgrupadosCentroCosto(7,2015);
        for (ResultadoQuery resultadoQuery : lista) {
            System.out.println(resultadoQuery.getNombre()+" : "+resultadoQuery.getImporte());
        }

    }
*/

    private Date buildDate(int dia, int mes,int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(anio, (mes - 1),dia);
        return cal.getTime();
    }

}
