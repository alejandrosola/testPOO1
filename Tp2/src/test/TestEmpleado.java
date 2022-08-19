package test;

import static org.junit.Assert.assertEquals;

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
		empleado1 = new BasePlusCommissionEmployee("Ale", "capo", "1234", 6, 0.3, 500.02);
		empleado2 = new CommissionEmployee("Eze", "Piola", "1235", 1, 1);
		empleado3 = new CommissionEmployee("Pibe", "Emoji", "1238", 13, 0.9);
		empleado4 = new HourlyEmployee("Agus", "copado", "1236", 23, 14);
		empleado5 = new SalariedEmployee("Ale2", "capo2", "1237", 2500);
		empleado6 = new HourlyEmployee("Test", "xd", "1239", 27, 50);
		empleado7 = new HourlyEmployee("Test", "xd", "1239", 50, 180);
		empleado8 = new SalariedEmployee("Ale3", "capo3", "1240", -3);
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
		// 40 * getWage() + ( getHours() - 40 ) * getWage() * 1.5
		assertEquals(0, empleado7.earnings(), 0.01); // hours * wage
	}
	
	@Test
	public void testSalaried() {
		assertEquals(2500, empleado5.earnings(), 0.01); // weeklySalary
		assertEquals(0, empleado8.earnings(), 0.01); // weeklySalary
//		weeklySalary = salary < 0.0 ? 0.0 : salary;
	}




}
