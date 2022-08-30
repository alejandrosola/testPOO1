package pruebas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import geometria.Elipse;
import geometria.Figura;
import geometria.FiguraCompuesta;
import geometria.Poligono;
import geometria.Punto;


public class PanelDibujo extends JPanel {

	private Elipse elipseGirada, elipseCentrada, circulo;
	private Poligono triangulo, cuadrado, rectangulo;
	private FiguraCompuesta fc;

	public PanelDibujo() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// elipse con radios 2 y 1, girada 90� y con centro en 3;3
		elipseGirada = new Elipse(20, 10, new Punto(30, 30), Math.PI / 2, Color.red, Color.black);

		// elipse con radios 3 y 2, sin girar y con centro en 0;0
		elipseCentrada = new Elipse(30, 20, Color.green, Color.green);

		// c�rculo con centro en 0;0:
		circulo = new Elipse(20, 20, Color.blue, Color.cyan);
		circulo.trasladar(50, 50);

		// tri�ngulo rect�ngulo:
		Punto[] t = { new Punto(30, 30), new Punto(30, 60), new Punto(70, 30) };
		triangulo = new Poligono(t, Color.black, Color.white);
		triangulo.trasladar(10, 10);

		// cuadrado:
		Punto[] c = { new Punto(0, 0), new Punto(0, 30), new Punto(30, 30), new Punto(30, 0) };
		cuadrado = new Poligono(c, Color.black, Color.yellow);
		cuadrado.trasladar(70, 70);

		// rect�ngulo:
		Punto[] r = { new Punto(0, 0), new Punto(0, 30), new Punto(50, 30), new Punto(50, 0) };
		rectangulo = new Poligono(r, Color.black, Color.red);

		elipseGirada.dibujar(g);
		elipseCentrada.dibujar(g);
		circulo.dibujar(g);

		// figura compuesta:
		Figura[] f = { triangulo, cuadrado, rectangulo };
		fc = new FiguraCompuesta(f);
		fc.trasladar(100, 100);
		fc.dibujar(g);
	}
}
