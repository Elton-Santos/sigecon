package com.sycon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {

	private int idContato;
	private String telefone;
	private String celular;
	private String email;
	private String celularAlternativo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContato")
	public int getIdContato() {
		return idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelularAlternativo() {
		return celularAlternativo;
	}

	public void setCelularAlternativo(String celularAlternativo) {
		this.celularAlternativo = celularAlternativo;
	}

}
