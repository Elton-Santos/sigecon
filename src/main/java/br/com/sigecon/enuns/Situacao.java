package br.com.sigecon.enuns;

public enum Situacao {
	COMPENSADO("Conpensado"), CACELADO("Cancelado"), PAGAMENTO_NAO_REALIZADO("Pagamento não Realizado");

	private String descricao;

	Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
