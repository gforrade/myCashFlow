package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.filters.CentroCostoFilter;
import ar.com.gaf.mycashflow.dao.filters.TarjetaCreditoFilter;
import ar.com.gaf.mycashflow.dao.filters.TipoGastoFilter;
import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.facade.CashFlowFacade;
import ar.com.gaf.mycashflow.facade.ForecastFacade;
import ar.com.gaf.mycashflow.model.CentroCostoEnum;
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

@Test(groups = "forecastTest")
public class ForecastTest {
	private static final Log log = LogFactory.getLog(ForecastTest.class);
	
	private static EJBContainer ejbContainer;

    private static ForecastFacade forecastFacade;

    private Long centroCostoId;

    @BeforeClass
    public static void setUpClass() throws Exception {
    	//ejbContainer = EJBContainer.createEJBContainer();
    	log.info("ejbContainer: " + ejbContainer);
    	// TODO: instanciar correctamente un contenedor... arquillian, glassfish-embedded, weblogic

        forecastFacade = (ForecastFacade)ContainerManager.getInstance().getContext().lookup("java:global/classes/ForecastFacadeBean");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    	if (ejbContainer != null) {
    		ejbContainer.close();
    	}
    }






    @Test
    public void udateTarjetasMesAnio() {
        forecastFacade.actualizarGastosTarjetas(8,2015);
    }



    private Date buildDate(int dia, int mes,int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(anio, (mes - 1),dia);
        return cal.getTime();
    }

}
