package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {

	private Domicilio direccion;
	private String email;
	private static final int maximoCuentas = 10;
	private List<CuentaBancaria> cuentas;

	public Cliente(String calle, int numero, String entre1, String entre2, String codigoPostal, String telefono,
			String email) {
		this.direccion = new Domicilio(calle, numero, entre1, entre2, codigoPostal, telefono);
		this.email = email;
		this.cuentas = new ArrayList<CuentaBancaria>();
	}

	public void pagarTarjetaCredito(double importe) {
		double temp = 0;

		for (CuentaBancaria c : cuentas)
			temp += c.getSaldo();
		
		if (temp < importe)
			throw new SaldoInsuficienteException("Saldo insuficiente");

		temp = 0;
		double restante = importe;
		// Pruebo con las caja ahorro
		for (CuentaBancaria c : cuentas)
			if (c.isCajaAhorro() && restante > 0)
				if (c.getSaldo() >= restante) {
					temp += restante;
					c.extraer(restante);
					restante -= temp;
				} else {
					temp += c.getSaldo();
					c.extraer(c.getSaldo());
					restante -= temp;
				}
		
		for (CuentaBancaria c : cuentas)
			if (c.isCuentaCorriente() && restante > 0)
				if (c.getSaldo() >= restante) {
					temp += restante;
					c.extraer(restante);
					restante -= temp;
				} else {
					temp += c.getSaldo();
					c.extraer(c.getSaldo());
					restante -= c.getSaldo();
				}
	}

	public Domicilio getDireccion() {
		return direccion;
	}

	public void setDireccion(Domicilio valor) {
		direccion = valor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String valor) {
		email = valor;
	}

	public List<CuentaBancaria> getCuentas() {
		return cuentas;
	}

	public void agregarCuenta(CuentaBancaria cuenta) {
		if (getCantidadCuentas() > maximoCuentas)
			throw new ClienteMaxCuentasException("Maximo de cuentas excedido");
		cuentas.add(cuentas.size(), cuenta);

	}

	public int getCantidadCuentas() {
		return this.cuentas.size();
	}

	public double saldoTotal() {
		double temp = 0;
		for (CuentaBancaria c : this.cuentas) {
			temp += c.getSaldo();
		}
		return temp;
	}

	private class Domicilio {
		String calle;
		int numero;
		String entre1;
		String entre2;
		String codigoPostal;
		String telefono;

		private Domicilio(String calle, int numero, String entre1, String entre2, String codigoPostal,
				String telefono) {
			this.calle = calle;
			this.numero = numero;
			this.entre1 = entre1;
			this.entre2 = entre2;
			this.codigoPostal = codigoPostal;
			this.telefono = telefono;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String valor) {
			telefono = valor;
		}

		public String getCalle() {
			return calle;
		}

		public String getCodigoPostal() {
			return codigoPostal;
		}

		public String[] getEntreCalles() {
			String[] entre = new String[2];
			entre[0] = entre1;
			entre[1] = entre2;
			return entre;
		}

		public int getNumero() {
			return numero;
		}
	}
}
