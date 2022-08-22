// Inmobiliaria.java
package inmobiliarias;

import java.util.ArrayList;
import java.util.List;

public class Inmobiliaria {
	private String nombre;
	private List<Inmueble> inmuebles;
	private double comisionClienteCompra; // atributo nuevo
	private double comisionClienteVenta; // atributo nuevo

	public Inmobiliaria(String nombre, double comisionClienteCompra, double comisionClienteVenta) {
		this.nombre = nombre;
		this.inmuebles = new ArrayList<Inmueble>();
		// se agregaron dos l�neas:
		this.comisionClienteCompra = comisionClienteCompra;
		this.comisionClienteVenta = comisionClienteVenta;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidadInmuebles() {
		return this.inmuebles.size();
	}

	public void agregarInmueble(Inmueble x) {
		inmuebles.add(x);
	}

	private int posicionInmueble(Inmueble x) {
		if (inmuebles == null)
			return -1;
		int pos = 0;
		for (Inmueble inm : inmuebles) {
			if (inm.equals(x))
				return pos;
			pos++;
		}
//		for (int pos = 0; pos < cantidadInmuebles; pos++)
//			if (inmuebles[pos] == x)
//				return pos;
		return -1;
	}

	public void quitarInmueble(Inmueble x) {
		int pos = posicionInmueble(x);
		if (pos > -1) { // encontr� el inmueble
			// voy a eliminar el elemento del arreglo por compresi�n
			inmuebles.remove(x);
		}
	}

	public void imprimirDatos() {
		System.out.println("Inmobiliaria: " + this.getNombre());
		if (inmuebles != null)
			for (Inmueble inm : inmuebles)
				inm.imprimirDatos();
		System.out.println();
	}

	// nueva propiedad:
	public double getComisionClienteCompra() {
		return comisionClienteCompra;
	}

	// nueva propiedad:
	public double getComisionClienteVenta() {
		return comisionClienteVenta;
	}

	// nuevo m�todo:
	public double beneficioEsperadoCartera() {
		double beneficio = 0;
		double comisionesCobradas = (getComisionClienteCompra() + getComisionClienteVenta()) / 100.0;

		for (Inmueble inm : inmuebles) {
			int precio = inm.getPrecio();
			if (!inm.getVendido()) {
				beneficio += precio * comisionesCobradas - inm.comisionVendedor();
			}
		}
		return beneficio;
	}

	// nuevo m�todo:
	public double beneficioEsperadoReservados() {
		double beneficio = 0;
		double comisionesCobradas = (getComisionClienteCompra() + getComisionClienteVenta()) / 100.0;
		for (Inmueble inm : inmuebles) {
			int precio = inm.getPrecio();
			if (!inm.getVendido() && inm.getReservado()) {
				beneficio += precio * comisionesCobradas - inm.comisionVendedor();
			}
		}
		return beneficio;
	}
}
