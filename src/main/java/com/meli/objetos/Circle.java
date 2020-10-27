package com.meli.objetos;

import com.meli.constantes.SatellitesPosition;

public class Circle {
	private SatellitesPosition satellitesPosition;
	private double radio;

	
	
	public Circle(SatellitesPosition satellitesPosition, float radio) {
		
		this.satellitesPosition = satellitesPosition;
		this.radio = radio;
	}

	public double getRadio() {
		return this.radio;
	}

	public double getCoordenadaX() {
		return satellitesPosition.getX();
	}

	public double getCoordenadaY() {
		return satellitesPosition.getY();
	}
}
