package com.paises.entities;

public class Coches {
	private String marca;
	private String modelo;
	private int iduser;

	

	public Coches(String marca,String modelo, int iduser) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.iduser = iduser;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public int getIduser() {
		return iduser;
	}



	public void setIduser(int iduser) {
		this.iduser = iduser;
	}



	@Override
	public String toString() {
		return "Coches [marca=" + marca + ", modelo=" + modelo + ", iduser=" + iduser + "]";
	}

	

}
