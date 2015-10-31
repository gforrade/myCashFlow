package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.model.CentroCostoEnum;
import ar.com.gaf.mycashflow.model.ResultadoQuery;
import ar.com.gaf.mycashflow.model.TarjetaCreditoEnum;
import ar.com.gaf.mycashflow.model.TipoGastoEnum;
import ar.com.gaf.mycashflow.model.entities.Moneda;
import ar.com.gaf.mycashflow.model.entities.TarjetaCredito;
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

@Test(groups = "cargaGastosTarjeta")
public class CargaGastosTarjetaTest {
	private static final Log log = LogFactory.getLog(CargaGastosTarjetaTest.class);
	
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
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaICBC, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 9498.00, buildDate(1, 10, 2014), "Heladera Nuevo Dto", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaICBC, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 8500.00, buildDate(1, 10, 2014), "Lavarropas Dto", Moneda.PESOS, 18);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaICBC, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 749.00, buildDate(1, 10, 2014), "Pava electrica", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaICBC, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 780.00, buildDate(1, 10, 2014), "Batidora", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.CMR, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 1864.44, buildDate(1, 11, 2014), "Aereo Casamiento Sebas", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 145.00, buildDate(1, 4, 2015), "Farmacia", Moneda.PESOS, 6);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 578.52, buildDate(1, 4, 2015), "Afeitadora Fravega", Moneda.PESOS, 12);


        //Tarjetas Caro
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 760.00, buildDate(1, 6, 2015), "Zapas Regalo dia del padre", Moneda.PESOS, 12);
//        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 1400.00, buildDate(1, 4, 2015), "Fase Electricidad - cosas para clinica", Moneda.PESOS, 6);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 570.00, buildDate(1, 6, 2015), "Boga Espacio Disenio /Carmar", Moneda.PESOS, 3);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 250.00, buildDate(1, 6, 2015), "Balanceo Auto / Leo Tejada", Moneda.PESOS, 2);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 2089.92, buildDate(1, 2, 2015), "XL (Extra Large) ", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 912.00, buildDate(1, 7, 2015), "Easy -Luang 12 cuotas Julio15 ", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 1980.00, buildDate(1, 3, 2015), "Un Lugar ", Moneda.PESOS, 6);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 225.00, buildDate(1, 3, 2015), "Cuspide Libros", Moneda.PESOS, 6);

        //Tarjetas Hijos
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 800.00, buildDate(1, 2 , 2015), "Mimo Triga Octubre 2015", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 566.00, buildDate(1, 10 , 2014), "Mimo Triga Octubre 2014", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 1125.00, buildDate(1, 3, 2015), "La Medalla Uniformes", Moneda.PESOS, 6);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 729.00, buildDate(1,11, 2014), "Zapas Tomi Martinazo", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 1440.00, buildDate(1,3, 2015), "Conejo Blanco", Moneda.PESOS, 12);
        //Tarjetas Varios
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.CMR, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 382.80, buildDate(1,1, 2015), "Seguro Hogar", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 432.80, buildDate(1,1, 2015), "Spotify Caro", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 432.80, buildDate(1,1, 2015), "Spotify Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 792.00, buildDate(1,1, 2015), "Mundo Gaturro", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 3425.00, buildDate(1,6, 2015), "Termotanque Mama", Moneda.PESOS, 12);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaHipotecario, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 320.00, buildDate(1,6, 2015), "Accesorios Termotanque Mama", Moneda.PESOS, 3);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 2364.00, buildDate(1,6, 2015), "Service Renault Clio", Moneda.PESOS, 3);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoCaro, 6207.00, buildDate(1,2, 2015), "Lavarropa Marianella", Moneda.PESOS, 12);
        //Tarjetas Super
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperCaro, TipoGastoEnum.TarjetaCreditoCaro, 291.00, new Date(System.currentTimeMillis()), "FM Alimentos", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperGaby, TipoGastoEnum.TarjetaCreditoCaro, 576.43, new Date(System.currentTimeMillis()), "Super Gaby", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperGaby, TipoGastoEnum.TarjetaCreditoCaro, 441.00, new Date(System.currentTimeMillis()), "Super Gaby", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperGaby, TipoGastoEnum.TarjetaCreditoCaro, 110.45, new Date(System.currentTimeMillis()), "Super Gaby", Moneda.PESOS, 1);
        //Tarjetas Nafta
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.NaftaGaby, TipoGastoEnum.TarjetaCreditoCaro, 763.67, new Date(System.currentTimeMillis()), "Nafta Gaby", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.NaftaCaro, TipoGastoEnum.TarjetaCreditoCaro, 540.50, new Date(System.currentTimeMillis()), "Nafta Caro", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.NaftaCaro, TipoGastoEnum.TarjetaCreditoCaro, 560.00, new Date(System.currentTimeMillis()), "Nafta Caro", Moneda.PESOS, 1);

        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoHijos, 75.00, buildDate(5, 7, 2015), "Cuspide Libros Regalo", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoVarios, 2860.00, buildDate(10, 7, 2015), "El Arca Operacion Abril", Moneda.PESOS, 6);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoVarios, 220.00, buildDate(10, 7, 2015), "El Arca Veterinaria", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoHijos, 85.00, buildDate(14, 7, 2015), "Mundo Barrilete", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperCaro, TipoGastoEnum.TarjetaCreditoCaro, 650.76, buildDate(14, 7, 2015), "Super Vea Caro", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoCaro, 205.00, buildDate(14, 7, 2015), "Play Cinema", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.NaftaCaro, TipoGastoEnum.TarjetaCreditoCaro, 560.13, buildDate(18, 7, 2015), "Nafta Caro", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.SuperCaro, TipoGastoEnum.TarjetaCreditoCaro, 480.35, buildDate(14, 7, 2015), "Super Atomo Caro", Moneda.PESOS, 1);

        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoGaby, 63.00, buildDate(19, 7, 2015), "Mac Donals", Moneda.PESOS, 1);
        cashFlowFacade.addGastoTarjetaCredito(TarjetaCreditoEnum.VisaSantanderRio, CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoGaby, 97.00, buildDate(19, 7, 2015), "Avicola Myriam", Moneda.PESOS, 1);



    }



    private Date buildDate(int dia, int mes,int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(anio, (mes - 1),dia);
        return cal.getTime();
    }

}
