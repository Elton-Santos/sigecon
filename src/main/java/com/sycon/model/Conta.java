package com.sycon.model;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.Transient;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idConta")
	private long idConta;
	private String descricaoConta;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contaCR")
	private List<ContaAReceber> receitas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contaCP")
	private List<ContaAPagar> despesas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Condominio condominioConta;
	
	@Transient
	private double totalDespesas;
	
	public double getTotalDespesas() {
		return totalDespesas;
	}

	public void setTotalDespesas(double totalDespesas) {
		this.totalDespesas = totalDespesas;
	}

	public double getTotalReceitas() {
		return totalReceitas;
	}

	public void setTotalReceitas(double totalReceitas) {
		this.totalReceitas = totalReceitas;
	}

	@Transient
	private double totalReceitas;
	
	public Condominio getCondominioConta() {
		return condominioConta;
	}

	public void setCondominioConta(Condominio condominioConta) {
		this.condominioConta = condominioConta;
	}

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	public String getDescricaoConta() {
		return descricaoConta;
	}

	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}


	public List<ContaAReceber> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<ContaAReceber> receitas) {
		this.receitas = receitas;
	}


	public List<ContaAPagar> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<ContaAPagar> despesas) {
		this.despesas = despesas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
