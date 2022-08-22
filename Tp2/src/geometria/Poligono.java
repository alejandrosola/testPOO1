// Poligono.java
package geometria;

import java.awt.Color;
import java.awt.Graphics;

public class Poligono extends Figura {

	private Punto[] contorno;
	private Color colorBorde;	
	private Color colorRelleno;

	public Poligono(Punto[] contorno, Color colorBorde, Color colorRelleno) {
		if (contorno.length < 3)
			// veremos el significado de lo que sigue en un cap�tulo posterior:
			throw new IllegalArgumentException();
		this.contorno = contorno;
		this.colorBorde = colorBorde;  	
		this.colorRelleno = colorRelleno;
	}

	static protected double areaTrapecio(Punto p1, Punto p2) {
		return (p1.getY() + p2.getY()) * (p2.getX() - p1.getX()) / 2;
	}

	public int numeroLados() {
		return contorno.length;
	}

	public double area() {
		double superficie = areaTrapecio(contorno[numeroLados() - 1],
				contorno[0]);
		for (int i = 0; i < numeroLados() - 1; i++)
			superficie += areaTrapecio(contorno[i], contorno[i + 1]);
		return superficie;
	}

	public double perimetro() {
		double longitud = contorno[numeroLados() - 1].distancia(contorno[0]);
		for (int i = 0; i < numeroLados() - 1; i++)
			longitud += contorno[i].distancia(contorno[i + 1]);
		return longitud;
	}

	private Segmento lado(int i) {
		if (i < numeroLados() - 1)
			return new Segmento(contorno[i], contorno[i + 1]);
		else
			return new Segmento(contorno[i], contorno[0]);
	}

	// verifica si es un pol�gono con todos sus lados iguales
	public boolean regular() {
		for (int i = 0; i < contorno.length - 1; i++) {
			if (lado(i).getLongitud() != lado(i + 1).getLongitud())
				return false;
		}
		return true;
	}

	public String tipo() {
		if (numeroLados() == 3 && regular())
			return "tri�ngulo equil�tero";
		if (numeroLados() == 4 && regular())
			return "cuadrado";
		String nombre = null;
		switch (numeroLados()) {
		case 3:
			nombre = "tri�ngulo";
			break;
		case 4:
			nombre = "cuadril�tero";
			break;
		case 5:
			nombre = "pent�gono";
			break;
		case 6:
			nombre = "hex�gono";
			break;
		case 7:
			nombre = "hept�gono";
			break;
		case 8:
			nombre = "oct�gono";
			break;
		case 9:
			nombre = "non�gono";
			break;
		case 10:
			nombre = "dec�gono";
			break;
		case 12:
			nombre = "dodec�gono";
			break;
		case 20:
			nombre = "icos�gono";
			break;
		default:
			nombre = "sin nombre";
		}
		if (regular())
			nombre += " regular";
		return nombre;
	}

	public void trasladar(double deltaX, double deltaY) {
		for (int i = 0; i < contorno.length; i++)
			contorno[i].trasladar(deltaX, deltaY);
	}

	public void dibujar(Graphics g) {
		int[] xPuntos = new int[contorno.length];
		int[] yPuntos = new int[contorno.length];

		for (int i = 0; i < contorno.length; i++) {
			xPuntos[i] = (int) contorno[i].getX();
			yPuntos[i] = (int) contorno[i].getY();
		}

		
		Color colorActual = g.getColor();
				
		g.setColor(colorBorde);
		g.drawPolygon(xPuntos, yPuntos, contorno.length);
		g.setColor(colorRelleno);
		g.fillPolygon(xPuntos, yPuntos, contorno.length);

		
		g.setColor(colorActual);
		
	}

}
