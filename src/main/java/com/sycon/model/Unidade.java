package com.sycon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unidade")
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_unidade")
	private long idUnidade;
	private String nomeUnidade;
	// private List<Pessoa> proprietarioUnidade;
	private double valorCondominio;
	

	@ManyToOne(fetch = FetchType.LAZY)
	private Condominio condominio;	

	
	public long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(long idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String unidade) {
		this.nomeUnidade = unidade;
	}

	/*
	 * @OneToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinColumn(name = "idPessoa") public List<Pessoa> getProprietarioUnidade() {
	 * return proprietarioUnidade; }
	 * 
	 * public void setProprietarioUnidade(List<Pessoa> proprietarioUnidade) {
	 * this.proprietarioUnidade = proprietarioUnidade; }
	 */

	public double getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}
	
	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

}
