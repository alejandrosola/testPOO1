package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import employee.BasePlusCommissionEmployee;
import employee.CommissionEmployee;
import employee.Employee;
import employee.HourlyEmployee;
import employee.SalariedEmployee;

public class TestEmpleado {

	private Employee empleado1, empleado2, empleado3, empleado4, empleado5, empleado6, empleado7, empleado8;
	
	@Before
	public void antesDelTest() {
		empleado1 = new BasePlusCommissionEmployee("Ale", "capo", "1234", 6, 0.3, 500.02, LocalDate.now());
		empleado2 = new CommissionEmployee("Eze", "Piola", "1235", 1, 1, LocalDate.of(2022, 12, 1));
		empleado3 = new CommissionEmployee("Pibe", "Emoji", "1238", 13, 0.9, LocalDate.of(2002, 9, 5));
		empleado4 = new HourlyEmployee("Agus", "copado", "1236", 23, 14, LocalDate.of(2000, 2, 7));
		empleado5 = new SalariedEmployee("Ale2", "capo2", "1237", 2500, LocalDate.of(1999, 1, 9));
		empleado6 = new HourlyEmployee("Test", "xd", "1239", 27, 50, LocalDate.of(2001, 9, 23));
		empleado7 = new HourlyEmployee("Test", "xd", "1239", 50, 180, LocalDate.of(1983, 8, 11));
		empleado8 = new SalariedEmployee("Ale3", "capo3", "1240", -3, LocalDate.of(2006, 3, 14));
	}
	
	@Test
	public void testBasePlusCommission() {
		assertEquals(501.82, empleado1.earnings(), 0.01); // 500.02 + grosssales * comissionrate
	}
	
	@Test
	public void testCommission() {
		assertEquals(0, empleado2.earnings(), 0.01); // grosssales * comissionrate
		assertEquals(11.7, empleado3.earnings(), 0.01); // grosssales * comissionrate

	}
	
	@Test
	public void testHourly() {
		assertEquals(322, empleado4.earnings(), 0.01); // hours * wage
		assertEquals(1485, empleado6.earnings(), 0.01); 
		assertEquals(0, empleado7.earnings(), 0.01); // hours * wage
	}
	
	@Test
	public void testSalaried() {
		assertEquals(2500, empleado5.earnings(), 0.01); // weeklySalary
		assertEquals(0, empleado8.earnings(), 0.01); // weeklySalary
	}
	
	@Test
	public void testSalario() {
		assertEquals(11.7, empleado3.salary(LocalDate.of(2022, 2, 3)), 0.01);
		assertEquals(501.82 + 999999, empleado1.salary(LocalDate.now()), 0.01);
		assertEquals(999999, empleado7.salary(LocalDate.of(2022, 8, 14)), 0.01);
	}

}
