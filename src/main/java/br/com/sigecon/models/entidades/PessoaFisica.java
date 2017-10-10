package br.com.sigecon.models.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "pessoaFisica")
public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column
	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotBlank(message = "O cpf é inválido")
	@CPF(message = "Digite um CPF válido")
	@Column(name = "cpf", unique=true)
	private String cpf;

	@Column(name = "rg")
	private String rg;

	@Column(name = "dataNascimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
