package br.com.sigecon.models.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "pessoafisica")
public class Usuario extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column(name = "senha ")
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
