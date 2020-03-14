package edu.udc.psw.formas;

import java.awt.Graphics;

public class Ponto<FormaGeomtrica> implements FormaGeometrica {
	private int x;
	private int y;
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ponto(Ponto p) {
		x = p.x;
		y = p.y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return String.format("(%d; %d", x, y);
	}
	
	public String getNome() {
		return "Ponto";
	}

	public Ponto centro() {
		return (Ponto)this.clone();
	}

	public double area() {
		return 0;
	}

	public double perimetro() {
		return 0;
	}

	public double base() {
		return 0;
	}

	public double altura() {
		return 0;
	}
	
	public double distancia(FormaGeometrica f) {
		Ponto c = f.centro();
		int dx = x - c.x;
		int dy = y - c.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}

	public Ponto getEnd() {
		return new Ponto(this);
	}

	public Ponto getStart() {
		return new Ponto(this);
	}

	@Override
	public Ponto clone() {
		return new Ponto(x, y);
	}

	@Override
	public void desenhar(Graphics g) {
		g.drawOval(x, y, 3, 3);
		
	}

}
