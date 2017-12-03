package com.sycon.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "contaAReceber")
public class ContaAReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCR")
	private long idCR;
	private String nomeCR;
	private String descricaoCR;
	private double valorCR;
	private String boleto;
	
	@Column(name = "dtVencimentoCR")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtVencimentoCR;
	
	@Column(name = "dtPagamentoCR")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtPagamentoCR;
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idUnidade")
	private Unidade unidadeCR;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Conta contaCR;

	public Conta getContaCR() {
		return contaCR;
	}

	public void setContaCR(Conta contaCR) {
		this.contaCR = contaCR;
	}


	public long getIdCR() {
		return idCR;
	}

	public void setIdCR(long idCR) {
		this.idCR = idCR;
	}

	public String getNomeCR() {
		return nomeCR;
	}

	public void setNomeCR(String nomeCR) {
		this.nomeCR = nomeCR;
	}

	public String getDescricaoCR() {
		return descricaoCR;
	}

	public void setDescricaoCR(String descricaoCR) {
		this.descricaoCR = descricaoCR;
	}

	public double getValorCR() {
		return valorCR;
	}

	public void setValorCR(double valorCR) {
		this.valorCR = valorCR;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	@Column(name = "dtVencimentoCR")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getDtVencimentoCR() {
		return dtVencimentoCR;
	}

	public void setDtVencimentoCR(Date dtVencimentoCR) {
		this.dtVencimentoCR = dtVencimentoCR;
	}

	@Column(name = "dtPagamentoCR")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getDtPagamentoCR() {
		return dtPagamentoCR;
	}

	public void setDtPagamentoCR(Date dtPagamentoCR) {
		this.dtPagamentoCR = dtPagamentoCR;
	}


	public Unidade getUnidadeCR() {
		return unidadeCR;
	}

	public void setUnidadeCR(Unidade unidadeCR) {
		this.unidadeCR = unidadeCR;
	}
}
