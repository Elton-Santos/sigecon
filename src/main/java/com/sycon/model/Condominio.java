package com.sycon.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "condominio")
public class Condominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCondominio")
	private long idCondominio;
	private String nomeCondominio;
	private Double taxaCondominio;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idEndereco")
	private Endereco enderecoCondominio;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "condominio")
	private List<Unidade> unidades;

	public Condominio() {
	}

	public Condominio(int idCondominio, String nomeCondominio, Double taxaCondominio, List<Unidade> unidades,
			Endereco enderecoCondominio) {
		this.idCondominio = idCondominio;
		this.nomeCondominio = nomeCondominio;
		this.taxaCondominio = taxaCondominio;
		this.unidades = unidades;
		this.enderecoCondominio = enderecoCondominio;
	}


	public long getIdCondominio() {
		return idCondominio;
	}

	public void setIdCondominio(long idCondominio) {
		this.idCondominio = idCondominio;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String condominio) {
		this.nomeCondominio = condominio;
	}


	public Endereco getEnderecoCondominio() {
		return enderecoCondominio;
	}

	public void setEnderecoCondominio(Endereco endereco) {
		this.enderecoCondominio = endereco;
	}

	public Double getTaxaCondominio() {
		return taxaCondominio;
	}

	public void setTaxaCondominio(Double taxaCondominio) {
		this.taxaCondominio = taxaCondominio;
	}

}
