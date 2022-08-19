package pruebas;

import junit.framework.TestCase;
import inmobiliarias.*;

public class PruebaInmobiliariasJUnit extends TestCase {

	private Inmueble membrillar, boyaca, artigas, neuquen, abitare;
	private Inmobiliaria sucasa, flores;
	
	protected void setUp( ) throws Exception {
		// creaci�n de objetos de prueba
		membrillar = new Terreno ("Membrillar 255", 85.3, 4, 82000, true);
		boyaca = new Casa ("Boyac� 1244", 144.3, 5, 124300, true, true, false, 253);
		artigas = new Departamento ("Artigas 1366", 95.2, 3, 93700, false, true);
		neuquen = new Departamento ("Neuqu�n 3455", 129.4, 5, 128400, true, true);
		sucasa = new Inmobiliaria ("Su casa", 3, 3);
		flores = new Inmobiliaria ("Flores", 3, 1.5);
		abitare = new Campo("Abitare", 294, 0, 441000, "Cordoba", 60);
		
		// carga de inmobiliarias
		sucasa.agregarInmueble(membrillar);
		sucasa.agregarInmueble(neuquen);
		flores.agregarInmueble(boyaca);
		flores.agregarInmueble(artigas);
		flores.agregarInmueble(abitare);
}

	public void testComisionVendedor( ) {
		// en terrenos la comisi�n es del 1%
		assertEquals (82000*0.01, membrillar.comisionVendedor( ), 1E-4);
		// en casas con jard�n y sin pileta la comisi�n es del 1%
		assertEquals (124300*0.01, boyaca.comisionVendedor( ), 1E-4);
		// en departamentos sin cochera la comisi�n es del 1,1%
		assertEquals (93700*0.011, artigas.comisionVendedor( ), 1E-4);
		// en departamentos con cochera la comisi�n es del 0,9%
		assertEquals (128400*0.009, neuquen.comisionVendedor( ), 1E-4);
		// en campo a 60km la comision es del 5%
		assertEquals (441000*0.05, abitare.comisionVendedor( ), 1E-4);
	}
	
	public void testBeneficioCartera( ) {
		final double beneficiosMembrillar = 82000 * (0.06-0.01);
		final double beneficiosNeuquen = 128400 * (0.06-0.009);
		assertEquals (beneficiosMembrillar + beneficiosNeuquen,
 					sucasa.beneficioEsperadoCartera( ), 1E-4);
		final double beneficiosBoyaca = 124300 * (0.045-0.01);
		final double beneficiosArtigas = 93700 * (0.045-0.011);
		final double beneficiosAbitare = 441000 * (0.045-0.05);
		assertEquals (beneficiosBoyaca + beneficiosArtigas + beneficiosAbitare,
 					flores.beneficioEsperadoCartera( ), 1E-4);
		// no hae inmuebles reservados:
		assertEquals (0, sucasa.beneficioEsperadoReservados( ), 1E-4);
		assertEquals (0, flores.beneficioEsperadoReservados( ), 1E-4);
		// reservamos dos inmuebles y vemos qu� pasa:
		membrillar.reservar( );
		artigas.reservar( );
		abitare.reservar();
		assertEquals (beneficiosMembrillar, sucasa.beneficioEsperadoReservados( ), 1E-4);
		assertEquals (beneficiosArtigas + beneficiosAbitare, flores.beneficioEsperadoReservados( ), 1E-4);
	}
}
