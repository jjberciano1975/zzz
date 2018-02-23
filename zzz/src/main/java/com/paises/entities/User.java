package com.paises.entities;

public class User {
	private String name;	
	private String email;
	private String password;
	private int idUser;
	public User(String name, String email, String password, int idUser) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", email=" + email + ", password"+password+", idUser=" + idUser + "]";
	}
	
}
