package br.com.sigecon.models.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "pessoaJuridica")
public class PessoaJuridica extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "razaoSocial")
	private String razaoSocial;

	@Column(name = "inscricaoEstadual")
	private String incricaoEstadual;

	public String getIncricaoEstadual() {
		return incricaoEstadual;
	}

	public void setIncricaoEstadual(String incricaoEstadual) {
		this.incricaoEstadual = incricaoEstadual;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
}
