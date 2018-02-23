package com.paises.entities;

import java.sql.Timestamp;

public class Partidas {



	private int iduser;
	private int codpart;
	private Timestamp fechapart;
	private int codequi;
	private String equipo;
	public Partidas(int iduser, int codpart, Timestamp fechapart, int codequi, String equipo) {
		super();
		this.iduser = iduser;
		this.codpart = codpart;
		this.fechapart = fechapart;
		this.codequi = codequi;
		this.equipo = equipo;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getCodpart() {
		return codpart;
	}
	public void setCodpart(int codpart) {
		this.codpart = codpart;
	}
	public Timestamp getFechapart() {
		return fechapart;
	}
	public void setFechapart(Timestamp fechapart) {
		this.fechapart = fechapart;
	}
	public int getCodequi() {
		return codequi;
	}
	public void setCodequi(int codequi) {
		this.codequi = codequi;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	@Override
	public String toString() {
		return "Partidas [iduser=" + iduser + ", codpart=" + codpart + ", fechapart=" + fechapart + ", codequi="
				+ codequi + ", equipo=" + equipo + "]";
	}	
	
}
