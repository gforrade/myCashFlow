package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.Moneda;
import ar.com.gaf.mycashflow.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ejb.embeddable.EJBContainer;
import java.util.Calendar;
import java.util.Date;

@Test(groups = "cargaUnGastoTarjeta")
public class CargaUnGastoTarjetaTest {
	private static final Log log = LogFactory.getLog(CargaUnGastoTarjetaTest.class);
	
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

/*
    @Test
    public void deleteAllTarjetasCredito() {
        List<TarjetaCredito> lis = tarjetaCreditoService.getAll();
        for (TarjetaCredito li : lis) {
            tarjetaCreditoService.remove(li);
        }
    }
*/


    @Test
    public void cargarGastosJulio() {

        //Tarjetas Gaby
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperGaby, TipoGastoEnum.TarjetaCreditoGaby, 288.00, new Date(System.currentTimeMillis()), "Super Vea", Moneda.PESOS, 1);



    }



    private Date buildDate(int dia, int mes,int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(anio, (mes - 1),dia);
        return cal.getTime();
    }

}
