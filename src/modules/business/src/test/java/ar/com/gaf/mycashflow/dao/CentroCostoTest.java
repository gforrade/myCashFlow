package ar.com.gaf.mycashflow.dao;

import ar.com.gaf.mycashflow.dao.filters.commons.Paginator;
import ar.com.gaf.mycashflow.model.entities.CentroCosto;
import ar.com.gaf.mycashflow.service.CentroCostoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ejb.embeddable.EJBContainer;
import java.util.List;

@Test(groups = "business")
public class CentroCostoTest {
	private static final Log log = LogFactory.getLog(CentroCostoTest.class);
	
	private static EJBContainer ejbContainer;
    private static CentroCostoService centroCostoService;

    private Long centroCostoId;

    @BeforeClass
    public static void setUpClass() throws Exception {
    	//ejbContainer = EJBContainer.createEJBContainer();
    	log.info("ejbContainer: " + ejbContainer);
    	// TODO: instanciar correctamente un contenedor... arquillian, glassfish-embedded, weblogic

    	centroCostoService = (CentroCostoService) ContainerManager.getInstance().getContext().lookup("java:global/classes/CentroCostoServiceBean");
    	log.info("centroCostoService: " + centroCostoService);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    	if (ejbContainer != null) {
    		ejbContainer.close();
    	}
    }



    public void findById() {
        CentroCosto centroCosto = centroCostoService.getById(new Long(100));
        System.out.println("centroCosto " + centroCosto);

/*
        Assert.assertNotEquals(person.getId(), 0);

        Person personFound = personService.getById(person.getId());

        Assert.assertNotNull(personFound);
*/

    }
    @Test
    public void create() {
        CentroCosto centroCosto = new CentroCosto("Gaby","Centro de Costo de Gabriel");
        centroCostoService.save(centroCosto);
        centroCostoId = centroCosto.getId();
    }

    @Test(dependsOnMethods = "create")
    public void update() {
        CentroCosto centroCosto = centroCostoService.getById(centroCostoId);
        centroCosto.setDescripcion("Modificado");
        centroCostoService.update(centroCosto);
    }

    @Test(dependsOnMethods = "update")
    public void findAll() {
        List<CentroCosto> centroCostoList = centroCostoService.getAll();
        for (CentroCosto centroCosto : centroCostoList) {
            System.out.println("centro costo: " + centroCosto);
        }
    }

    @Test(dependsOnMethods = "findAll")
    public void remove() {
        CentroCosto centroCosto = centroCostoService.getById(centroCostoId);
        centroCostoService.remove(centroCosto);
    }


}
