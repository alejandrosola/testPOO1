package inmobiliarias;

public class Campo extends Inmueble{
	
	private String ciudad;
	private int distKm;
	private static int distComision = 100;
	
	public Campo(String domicilio, double superficie, int cantidadAmbientes,
			int precio,String ciudad,int distKm ) {
		super(domicilio, superficie, cantidadAmbientes, precio);
		this.ciudad = ciudad;
		this.distKm = distKm;
	}

	
	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public int getDistKm() {
		return distKm;
	}


	public void setDistKm(int distKm) {
		this.distKm = distKm;
	}


	public static int getDistComision() {
		return distComision;
	}


	public static void setDistComision(int distComision) {
		Campo.distComision = distComision;
	}


	public void imprimirDatos( ) {
		super.imprimirDatos( );
		System.out.println ("Ciudad mas cercana: " + getCiudad( ));
		System.out.println ("Distancia: " + getDistKm( ));
	}
	
	@Override
	public double comisionVendedor() {
		if (distKm<distComision)
			return getPrecio() * 0.05;
		return getPrecio() * 0.1;
	}
}
