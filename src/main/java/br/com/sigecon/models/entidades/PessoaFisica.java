package br.com.sigecon.models.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "pessoaFisica")
public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "rg")
	private String rg;

	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", rg=" + rg + ", toString()=" + super.toString() + "]";
	}

}
