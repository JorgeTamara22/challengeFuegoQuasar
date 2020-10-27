package com.meli.objetos;

public class Response {

	public Response(Position position, String message) {
		super();
		this.position = position;
		this.message = message;
	}

	private Position position;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
