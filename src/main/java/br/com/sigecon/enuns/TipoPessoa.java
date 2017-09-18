package br.com.sigecon.enuns;

public enum TipoPessoa {
	MORADOR("Morador"), VISITANTE("Visitante"), VISITANTE_AUTORIZADO("Visitante Autorizado"), SEGURANÇA(
			"Seguranca"), ADMINISTRADOR("Administrador"), FORNECEDOR(
					"Fornecedor"), FUNCIONARIO("Funcionario"), PROPRIETARIO("Proprietario");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
