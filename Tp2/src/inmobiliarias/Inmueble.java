// Inmueble.java
package inmobiliarias;

import java.util.ArrayList;
import java.util.List;

public abstract class Inmueble {

	private String domicilio;
	private double superficie;
	private int cantidadAmbientes;
	private int precio;
	private List<Cliente> interesados;
	private boolean reservado;
	private boolean vendido;
		
	public Inmueble (String domicilio, double superficie, int cantidadAmbientes, int precio) {
		this.domicilio = domicilio;
		this.superficie = superficie;
		this.cantidadAmbientes = cantidadAmbientes;
		this.precio = precio;
		this.interesados = new ArrayList<Cliente>();
		this.reservado = false;
		this.vendido = false;
	}
		
	public String getDomicilio() {
		return domicilio;
	}
		
	public double getSuperficie() {
		return superficie;
	}
		
	public int getCantidadAmbientes() {
		return cantidadAmbientes;
	}
		
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int nuevo) {
		precio = nuevo;
		if (interesados != null)
			for (Cliente interesado : interesados)
				interesado.avisarCambioPrecio(this, nuevo);
	}
		
	public void anotarInteresado (Cliente c) {
		interesados.add(c);
	}

	private int posicionInteresado (Cliente c) {
		if (interesados == null)
			return -1;
		int pos = 0;
		for (Cliente interesado : interesados) {
			if (interesado == c)
				return pos;
			pos++;
		}
		return -1;
	}

	public void eliminarInteresado (Cliente c) {
		int pos = posicionInteresado(c);
		if (pos > -1) {	// encontr� el cliente
			// voy a eliminar el elemento del arreglo por compresi�n
			interesados.remove(c);
		}
	}

	public boolean getReservado( ) {
		return reservado;
	}
			
	public void reservar( ) {
		reservado = true;
		for (Cliente interesado : interesados) {
			interesado.avisarReserva(this);
		}
	}
		
	public boolean getVendido( ) {
		return vendido;
	}
		
	public void vender( ) {
		vendido = true;
		for (Cliente interesado : interesados) {
			interesado.avisarVenta(this);
		}
	}
		
	public void imprimirDatos( ) {
		System.out.println ("Domicilio: " + getDomicilio( ));
		System.out.println ("Superficie: " + getSuperficie( ));
		System.out.println ("Cantidad de ambientes: " + getCantidadAmbientes( ));
		System.out.println ("Precio: " + getPrecio( ));
		System.out.println ("Reservado: " + getReservado( ));
		System.out.println ("Vendido: " + getVendido( ));
		System.out.println ("Cantidad de interesados: " + this.interesados.size());
		
		for (Cliente interesado : interesados) {
			System.out.println(interesado.getNombre());
		}
	}
	
	// nuevo m�todo:
	public abstract double comisionVendedor( );

}
