package com.meli.objetos;

import java.util.List;

public class Satelite {
	
	
	public Satelite(String name, float distance, List<String> message) {
		super();
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

	private String name;
	
	private float distance;
	
	private List<String> message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
