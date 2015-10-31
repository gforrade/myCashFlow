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

@Test(groups = "cargaGastos")
public class CargaDatosTest {
	private static final Log log = LogFactory.getLog(CargaDatosTest.class);
	
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




    private void createCentroCosto(String nombre, String descripcion ) {
        CentroCosto centroCosto = new CentroCosto(nombre,descripcion);
        centroCostoService.save(centroCosto);
    }


    private void createTarjetaCredito(String nombre, String descripcion , String bancoEmisor) {
        TarjetaCredito tarjetaCredito = new TarjetaCredito();
        tarjetaCredito.setNombre(nombre);
        tarjetaCredito.setDescripcion(descripcion);
        tarjetaCredito.setBancoEmisor(bancoEmisor);
        tarjetaCreditoService.save(tarjetaCredito);
    }

    private void createTipoGasto(String nombre, GrupoGasto grupoGasto) {
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setNombre(nombre);
        tipoGasto.setDescripcion("Descripcion de nombre");
        tipoGasto.setGrupoGasto(grupoGasto);
        tipoGastoService.save(tipoGasto);
    }

    private void deleteAllCentroCostos() {
        List<CentroCosto> lis = centroCostoService.getAll();
        for (CentroCosto li : lis) {
            centroCostoService.remove(li);
        }
    }

    private void deleteAllTarjetasCredito() {
        List<TarjetaCredito> lis = tarjetaCreditoService.getAll();
        for (TarjetaCredito li : lis) {
            tarjetaCreditoService.remove(li);
        }
    }

    private void deleteAllTipoGastos() {
        List<TipoGasto> lis = tipoGastoService.getAll();
        for (TipoGasto li : lis) {
            tipoGastoService.remove(li);
        }
    }

    private void deleteAllGastos() {
        List<GastoEfectivo> lis = gastoService.getAll();
        for (GastoEfectivo li : lis) {
            gastoService.remove(li);
        }
    }

    private void deleteAllGastosTarjetas() {
        List<GastoTarjetaCredito> lis = gastoTarjetaCreditoService.getAll();
        for (GastoTarjetaCredito li : lis) {
            gastoTarjetaCreditoService.remove(li);
        }
    }


    private void createGasto(CentroCosto centroCosto, TipoGasto tipoGasto, Double importe, Date fechaCompra, String descripcion, Moneda moneda , int meses) {
        GastoEfectivo gasto = new GastoEfectivo(centroCosto,tipoGasto,importe,fechaCompra,descripcion,moneda);
        if (meses == 1) {
            gastoService.save(gasto);
        } else {
            gastoService.save(gasto,meses);
        }
    }

    private void createGastoTarjeta(Gasto gasto, TarjetaCredito tarjetaCredito, int nroCuotas ) {
        GastoTarjetaCredito gastoTarjetaCredito = new GastoTarjetaCredito(tarjetaCredito,nroCuotas);
        gastoTarjetaCredito.setCentroCosto(gasto.getCentroCosto());
        gastoTarjetaCredito.setDescripcion(gasto.getDescripcion());
        gastoTarjetaCredito.setFechaCompra(gasto.getFechaCompra());
        gastoTarjetaCredito.setImporte(gasto.getImporte());
        gastoTarjetaCredito.setMoneda(gasto.getMoneda());
        gastoTarjetaCredito.setTipoGasto(gasto.getTipoGasto());
        gastoTarjetaCreditoService.save(gastoTarjetaCredito);
    }


    private  TipoGasto getTipoGasto(String nombre) {
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);

        TipoGastoFilter tipoGastoFilter = new TipoGastoFilter();
        tipoGastoFilter.setNombre(nombre);
        List<TipoGasto> tipoGastoList = tipoGastoService.getByFilter(tipoGastoFilter,paginator);
        return tipoGastoList.get(0);
    }

    private  TarjetaCredito getTarjetaCredito(String nombre) {
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);

        TarjetaCreditoFilter tarjetaCreditoFilter  = new TarjetaCreditoFilter();
        tarjetaCreditoFilter.setNombre(nombre);
        List<TarjetaCredito> tarjetas = tarjetaCreditoService.getByFilter(tarjetaCreditoFilter,paginator);
        return tarjetas.get(0);

    }

    private  CentroCosto getCentroCosto(String nombre) {

        CentroCostoFilter filter = new CentroCostoFilter();
        filter.setNombre(nombre);
        Paginator paginator = new Paginator();
        paginator.setFirst(0);
        paginator.setPageSize(1);
        List<CentroCosto> centroCostoList = centroCostoService.getByFilter(filter,paginator);
        return centroCostoList.get(0);
    }




    public void deleteAll() {
//        deleteAllGastos();
//        deleteAllGastosTarjetas();
//        deleteAllCentroCostos();
//        deleteAllTarjetasCredito();
//        deleteAllTipoGastos();
    }

/*    @Test(dependsOnMethods = "deleteAll")
    public void createCentrosCostos() {
        createCentroCosto("Gaby","Centro Costo Gaby");
        createCentroCosto("Caro","Centro Costo Caro");
        createCentroCosto("Hijos", "Centro Costo Hijos");
        createCentroCosto("Varios", "Centro Costo Varios");
        createCentroCosto("Familia", "Centro Costo Familia");
        createCentroCosto("SuperCaro", "Centro Costo Super Caro");
        createCentroCosto("SuperGaby", "Centro Costo Super Gaby");
        createCentroCosto("NaftaCaro", "Centro Costo Nafta Caro");
        createCentroCosto("NaftaGaby", "Centro Costo Nafta Gaby");



    }

    @Test(dependsOnMethods = "createCentrosCostos")
    public void createTiposGastos() {
        //Tipo Gastos VIVIENDA
        createTipoGasto(TipoGastoEnum.ExpensasDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.ExpensasMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.CelularCaro.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.LuzMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.LuzMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.LuzDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.MunicipalidadDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.GasDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.AguaDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.AlquilerDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.DirecTVMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.DirecTVDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.JardineroMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.EmpleadaMedano.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.SpeedyDtoGaby.name(),GrupoGasto.VIVIENDA);
        createTipoGasto(TipoGastoEnum.xFlyMedano.name(),GrupoGasto.VIVIENDA);


        //Tipo Gastos TRANSPORTE
        createTipoGasto(TipoGastoEnum.SeguroClio.name(),GrupoGasto.TRANSPORTE);
        createTipoGasto(TipoGastoEnum.PatenteClio.name(),GrupoGasto.TRANSPORTE);
        createTipoGasto(TipoGastoEnum.NaftaClio.name(),GrupoGasto.TRANSPORTE);
        createTipoGasto(TipoGastoEnum.Seguro308.name(),GrupoGasto.TRANSPORTE);
        createTipoGasto(TipoGastoEnum.Patente308.name(),GrupoGasto.TRANSPORTE);
        createTipoGasto(TipoGastoEnum.Nafta308.name(),GrupoGasto.TRANSPORTE);

        //Tipo Gastos IMPUETSOS
        createTipoGasto(TipoGastoEnum.Monotributo.name(),GrupoGasto.IMPUESTOS);

        //Tipo Gastos SALUD
        createTipoGasto(TipoGastoEnum.OSDE.name(),GrupoGasto.SALUD);
        createTipoGasto(TipoGastoEnum.SicologoCaro.name(),GrupoGasto.SALUD);
        createTipoGasto(TipoGastoEnum.SicologoGaby.name(),GrupoGasto.SALUD);
        createTipoGasto(TipoGastoEnum.SicologoTomi.name(),GrupoGasto.SALUD);



        //Tipo Gastos TARJETAS
        createTipoGasto(TipoGastoEnum.GastosMantenimentoTarjetas.name(),GrupoGasto.TARJETAS_CREDITO);
        createTipoGasto(TipoGastoEnum.TarjetaCreditoHijos.name(),GrupoGasto.TARJETAS_CREDITO);
        createTipoGasto(TipoGastoEnum.TarjetaCreditoGaby.name(),GrupoGasto.TARJETAS_CREDITO);
        createTipoGasto(TipoGastoEnum.TarjetaCreditoCaro.name(),GrupoGasto.TARJETAS_CREDITO);
        createTipoGasto(TipoGastoEnum.TarjetaCreditoVarios.name(),GrupoGasto.TARJETAS_CREDITO);

        //Tipo Gastos EDUCACION
        createTipoGasto(TipoGastoEnum.CuotaColegioTomas.name(),GrupoGasto.EDUCACION);
        createTipoGasto(TipoGastoEnum.CuotaColegioAnaPaula.name(),GrupoGasto.EDUCACION);


        //Tipo Gastos ESPARCIMIENTO
        createTipoGasto(TipoGastoEnum.Rugby.name(),GrupoGasto.ESPARCIMIENTO);
        createTipoGasto(TipoGastoEnum.Danza.name(),GrupoGasto.ESPARCIMIENTO);
        createTipoGasto(TipoGastoEnum.GimnasioGaby.name(),GrupoGasto.ESPARCIMIENTO);
        createTipoGasto(TipoGastoEnum.NatacionCaro.name(),GrupoGasto.ESPARCIMIENTO);

        //Tipo Gastos PRESTAMOS
        createTipoGasto(TipoGastoEnum.Cuota308.name(),GrupoGasto.PRESTAMOS);
        createTipoGasto(TipoGastoEnum.CuotaClio.name(),GrupoGasto.PRESTAMOS);
        createTipoGasto(TipoGastoEnum.PrestamoPersonal.name(),GrupoGasto.PRESTAMOS);
        createTipoGasto(TipoGastoEnum.CreditoHipotecario.name(),GrupoGasto.PRESTAMOS);

        //Tipo Gastos ALIMNENTACION
        createTipoGasto(TipoGastoEnum.SuperCaro.name(),GrupoGasto.ALIMENTACION);
        createTipoGasto(TipoGastoEnum.SuperGaby.name(),GrupoGasto.ALIMENTACION);


    }

    @Test(dependsOnMethods = "createTiposGastos")
    public void createTarjetasCredito() {
        createTarjetaCredito("VisaSantanderRio", "VISA", "Santander Rio");
        createTarjetaCredito("VisaICBC", "VISA", "ICBC");
        createTarjetaCredito("VisaHipotecario", "VISA", "Banco Hipotecario");
        createTarjetaCredito("AmericanExpress", "VISA", "American Express");
        createTarjetaCredito("CMR", "CMR", "Falabella");
    }*/



    @Test
    public void createGastos() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 0, 1);


        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.ExpensasMedano, 400.00, cal.getTime(), "Expensas Mensuales Medano", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.ExpensasDtoGaby, 452.00, cal.getTime(), "Expensas Mensuales Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.AlquilerDtoGaby, 4144.00, cal.getTime(), "Alquiler Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.CelularCaro, 150.00, cal.getTime(), "Celu Caro", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.LuzMedano, 400.00, cal.getTime(), "Luz Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.LuzDtoGaby, 400.00, cal.getTime(), "Luz Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.AguaDtoGaby, 164.00, cal.getTime(), "Agua Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.DirecTVDtoGaby, 180.00, cal.getTime(), "Directv Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.DirecTVMedano, 250.00, cal.getTime(), "Directv", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.SpeedyDtoGaby, 200.00, cal.getTime(), "Internet", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.xFlyMedano, 340.00, cal.getTime(), "Internet", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Seguro308, 787.00, cal.getTime(), "Seguro", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.SeguroClio, 502.00, cal.getTime(), "Seguro", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.NaftaGaby, TipoGastoEnum.Nafta308, 1500.00, cal.getTime(), "Combustible", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.NaftaCaro, TipoGastoEnum.NaftaClio, 1500.00, cal.getTime(), "Combustible", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.OSDE, 2100.00, cal.getTime(), "OSDE", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.CuotaColegioTomas, 1900.00, cal.getTime(), "OSDE", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.CuotaColegioAnaPaula, 1500.00, cal.getTime(), "OSDE", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Caro, TipoGastoEnum.TarjetaCreditoCaro, 2000.00, cal.getTime(), "Gastos Tarjeta Caro", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Gaby, TipoGastoEnum.TarjetaCreditoGaby, 2000.00, cal.getTime(), "Gastos Tarjeta Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Hijos, TipoGastoEnum.TarjetaCreditoHijos, 1500.00, cal.getTime(), "Gastos Tarjeta Hijos", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Varios, TipoGastoEnum.TarjetaCreditoVarios, 1500.00, cal.getTime(), "Gastos Tarjeta Varios", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.CreditoHipotecario, 2730.00, cal.getTime(), "Credito Hipotecario", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.PrestamoPersonal, 3800.00, cal.getTime(), "Credito Personal Santander Rio", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Cuota308, 3446.00, cal.getTime(), "Cuota Peugeot 308", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.CuotaClio, 2075.00, cal.getTime(), "Cuota Clio", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.SuperCaro, TipoGastoEnum.SuperCaro, 4000.00, cal.getTime(), "Super Caro", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.SuperGaby, TipoGastoEnum.SuperGaby, 3000.00, cal.getTime(), "Super Gaby", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Danza, 440.00, cal.getTime(), "Danza Anita", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Rugby, 200.00, cal.getTime(), "Rugby Tomi", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.SicologoGaby, 400.00, cal.getTime(), "Sicologo Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.GimnasioGaby, 380.00, cal.getTime(), "Gym Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.NatacionCaro, 380.00, cal.getTime(), "Natacion Caro", Moneda.PESOS, 12);

        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Danza, 440.00, cal.getTime(), "Danza Anita", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.Rugby, 200.00, cal.getTime(), "Rugby Tomi", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.SicologoGaby, 400.00, cal.getTime(), "Sicologo Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.GimnasioGaby, 380.00, cal.getTime(), "Gym Gaby", Moneda.PESOS, 12);
        cashFlowFacade.addGasto(CentroCostoEnum.Familia, TipoGastoEnum.NatacionCaro, 380.00, cal.getTime(), "Natacion Caro", Moneda.PESOS, 12);



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
