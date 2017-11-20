package com.sycon.model;

public enum TipoPessoa {

	ADMINISTRADOR("Administrador"), FORNECEDOR("Fornecedor"), 
		FUNCIONARIO("Funcionario"), PROPRIETARIO("Proprietario");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
