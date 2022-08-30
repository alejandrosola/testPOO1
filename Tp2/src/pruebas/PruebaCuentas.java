package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import modelo.CajaAhorro;
import modelo.Cliente;
import modelo.CuentaBancaria;
import modelo.CuentaCorriente;
import modelo.Empresa;
import modelo.Persona;
import modelo.SaldoInsuficienteException;

public class PruebaCuentas extends TestCase {

	private CuentaBancaria cajaAhorro1;
	private CuentaBancaria cuentaCorriente1;
	private CuentaBancaria cajaAhorro2;
	private CuentaBancaria cajaAhorro3;
	private CuentaBancaria cajaAhorro4;
	private CuentaBancaria cuentaCorriente2;
	private CuentaBancaria cuentaCorriente3;
	private Cliente juan;
	private Cliente ines;
	private Cliente ale;
	private Cliente empresa;

	protected void setUp() throws Exception {
		super.setUp();
		juan = new Persona("Juan", "P�rez", 5122122, "Lima", 444, "Belgrano", "Venezuela", "C1000AAA", "01140101010",
				"juan@perez.com.ar");
		ale = new Persona("Ale", "Solá", 44046991, "Alvear", 492, "sdasd", "asdasd", "1asdasd", "adsdsd", "ss");
		ines = new Persona("In�s", "Garc�a", 4011011, "Cerrito", 1111, "Santa Fe", "Arenales", "C1111ZZZ",
				"01140001111", "ines@garcia.com.ar");
		empresa = new Empresa("Luna Nueva S.A.", "1701234562", "Freire", 8888, "Dorrego", "Concepci�n Arenal",
				"C0000YYY", "01199991000", "info@empresa.com.ar");
		cajaAhorro1 = new CajaAhorro(1234, juan);
		cajaAhorro2 = new CajaAhorro(5678, ines);
		cajaAhorro3 = new CajaAhorro(8910, ale);
		cajaAhorro4 = new CajaAhorro(1112, ale);

		cuentaCorriente1 = new CuentaCorriente(9012, ines, 0);
		cuentaCorriente2 = new CuentaCorriente(3456, empresa, 2000);
		cuentaCorriente3 = new CuentaCorriente(9730, ale, 123);
		cajaAhorro3.depositar(2000);
		cajaAhorro4.depositar(390.01);
		cuentaCorriente3.depositar(201.02);
		cajaAhorro3.extraer(100);
		cuentaCorriente3.extraer(101);
	}

	public void test() {
		assertEquals(1, juan.getCantidadCuentas());
		assertEquals(2, ines.getCantidadCuentas());
		assertEquals(1, empresa.getCantidadCuentas());
		assertEquals(juan, cajaAhorro1.getTitular());
		assertEquals(ines, cajaAhorro2.getTitular());
		assertEquals(ines, cuentaCorriente1.getTitular());
		assertEquals(empresa, cuentaCorriente2.getTitular());
		List<CuentaBancaria> cuentasJuan = juan.getCuentas();
		List<CuentaBancaria> cuentasInes = ines.getCuentas();
		List<CuentaBancaria> cuentasEmpresa = empresa.getCuentas();
		assertEquals(cajaAhorro1, cuentasJuan.get(0));
		assertEquals(cajaAhorro2, cuentasInes.get(0));
		assertEquals(cuentaCorriente1, cuentasInes.get(1));
		assertEquals(cuentaCorriente2, cuentasEmpresa.get(0));
		// Test saldoTotal
		assertEquals(0, juan.saldoTotal(), 0.01);
		assertEquals(2390.03, ale.saldoTotal(), 0.01);
	}
	@Test
	public void testTarjeta() {
		Assert.assertThrows(SaldoInsuficienteException.class, () -> {
			ale.pagarTarjetaCredito(2500.50);
		});
		ale.pagarTarjetaCredito(1800);
		Assert.assertEquals(100, cajaAhorro3.getSaldo(), 0.01);
		Assert.assertEquals(390.01, cajaAhorro4.getSaldo(), 0.01);
		
		ale.pagarTarjetaCredito(200);
		Assert.assertEquals(0, cajaAhorro3.getSaldo(), 0.01);
		Assert.assertEquals(290.01, cajaAhorro4.getSaldo(), 0.01);
		Assert.assertEquals(100.02, cuentaCorriente3.getSaldo(), 0.01);
	}
} 








