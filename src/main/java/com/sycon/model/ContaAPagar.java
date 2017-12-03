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
@Table(name = "contaAPagar")
public class ContaAPagar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCP")
	private long idCP;
	private String nomeCP;
	private String descricaoCP;
	private double valorCP;
	private String notaFiscal;

	@Column(name = "dtVencimentoCP")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtVencimentoCP;

	@Column(name = "dtPagamentoCP")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtPagamentoCP;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idpessoa")
	private Pessoa pessoaCP;

	@ManyToOne(fetch = FetchType.LAZY)
	private Conta contaCP;

	public Conta getContaCP() {
		return contaCP;
	}

	public void setContaCP(Conta contaCP) {
		this.contaCP = contaCP;
	}

	public long getIdCP() {
		return idCP;
	}

	public void setIdCP(long idCP) {
		this.idCP = idCP;
	}

	public String getNomeCP() {
		return nomeCP;
	}

	public void setNomeCP(String nomeCP) {
		this.nomeCP = nomeCP;
	}

	public String getDescricaoCP() {
		return descricaoCP;
	}

	public void setDescricaoCP(String descricaoCP) {
		this.descricaoCP = descricaoCP;
	}

	public double getValorCP() {
		return valorCP;
	}

	public void setValorCP(double valorCP) {
		this.valorCP = valorCP;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Date getDtVencimentoCP() {
		return dtVencimentoCP;
	}

	public void setDtVencimentoCP(Date dtVencimentoCP) {
		this.dtVencimentoCP = dtVencimentoCP;
	}

	public Date getDtPagamentoCP() {
		return dtPagamentoCP;
	}

	public void setDtPagamentoCP(Date dtPagamentoCP) {
		this.dtPagamentoCP = dtPagamentoCP;
	}

	public Pessoa getPessoaCP() {
		return pessoaCP;
	}

	public void setPessoaCP(Pessoa pessoaCP) {
		this.pessoaCP = pessoaCP;
	}
}
