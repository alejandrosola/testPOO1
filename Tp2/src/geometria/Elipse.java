// Elipse.java
package geometria;

import java.awt.Color;
import java.awt.Graphics;

public class Elipse extends Figura {

	private double radioMayor;
	private double radioMenor;
	private Punto centro;
	private double anguloRadioMayor;
	private Color colorBorde;
	private Color colorRelleno;

	public Elipse(double radioMayor, double radioMenor, Punto centro,
			double anguloRadioMayor, Color colorBorde, Color colorRelleno) {
		this.radioMayor = radioMayor;
		this.radioMenor = radioMenor;
		this.centro = centro;
		this.anguloRadioMayor = anguloRadioMayor;
		this.colorBorde = colorBorde;  		
		this.colorRelleno = colorRelleno;
	}

	public Elipse(double radioMayor, double radioMenor, Color colorBorde, Color colorRelleno) {
		this.radioMayor = radioMayor;
		this.radioMenor = radioMenor;
		this.centro = new Punto(0, 0);
		this.anguloRadioMayor = 0;
		this.colorBorde = colorBorde;  		
		this.colorRelleno = colorRelleno;
	}

	public double getRadioMayor() {
		return radioMayor;
	}

	public double getRadioMenor() {
		return radioMenor;
	}

	public Punto getCentro() {
		return centro;
	}

	public double getAnguloRadioMayor() {
		return anguloRadioMayor;
	}

	public double area() {
		return Math.PI * getRadioMayor() * getRadioMenor();
	}

	private static double E1(double k) {
		// TODO: tabla de integral el�ptica
		return 0;
	}

	public double perimetro() {
		// caso del c�rculo:
		if (getRadioMayor() == getRadioMenor())
			return (Math.PI * 2 * getRadioMayor());
		// caso general:
		double k = Math.sqrt(Math.pow(getRadioMayor(), 2)
				- Math.pow(getRadioMenor(), 2))
				/ Math.pow(getRadioMayor(), 2);
		return (4 * getRadioMayor() * E1(k));
	}

	public String tipo() {
		if (getRadioMayor() == getRadioMenor())
			return "c�rculo";
		else
			return "elipse";
	}

	public void trasladar(double deltaX, double deltaY) {
		centro.trasladar(deltaX, deltaY);
	}

	public void dibujar(Graphics g) {
		Color colorActual = g.getColor();
				
		g.setColor(colorBorde);
		g.drawOval((int)centro.getX(), (int)centro.getY(), (int)radioMenor, (int)radioMayor);
		g.setColor(colorRelleno);
		g.fillOval((int)centro.getX(), (int)centro.getY(), (int)radioMenor, (int)radioMayor);
		
		g.setColor(colorActual);
	}

}
