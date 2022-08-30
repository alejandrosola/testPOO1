package modelo;

public class CajaAhorro extends CuentaBancaria {
	
	private double interesesGanados;

	public CajaAhorro (int numero, Cliente titular) {
		super (numero, titular);
		this.interesesGanados = 0;
	}

	public boolean isCajaAhorro() {
		return true;
	}
	
	public boolean isCuentaCorriente() {
		
		return false;
	}
	
	public double getInteresesGanados ( ) {
			return interesesGanados;
	}
	
	public void setInteresesGanados (double valor) {
			interesesGanados = valor;
		}

	public void pagarIntereses( ) {
		setSaldo ( getSaldo() + interesesGanados );
		interesesGanados = 0;
	}
	
	public void extraer(double monto) {
		if (monto > this.getSaldo())
		throw new SaldoInsuficienteException("Saldo insuficiente");
	else
		this.setSaldo(this.getSaldo() - monto);
	}

	
}
