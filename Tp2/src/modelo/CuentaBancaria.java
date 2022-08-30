package modelo;

public abstract class CuentaBancaria {

	private int numero;
	private Cliente titular;
	private double saldo;

	public CuentaBancaria(int numero, Cliente titular) {
		titular.agregarCuenta(this);
		this.numero = numero;
		this.titular = titular;
		this.saldo = 0;

	}
	
	public abstract boolean isCuentaCorriente();
	
	public abstract boolean isCajaAhorro();
	
	public int getNumero() {
		return numero;
	}

	public Cliente getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void depositar(double monto) {
		saldo += monto;
	}

	public abstract void extraer(double monto);

}
