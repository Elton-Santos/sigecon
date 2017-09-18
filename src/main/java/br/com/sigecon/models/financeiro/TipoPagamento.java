package br.com.sigecon.models.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoDePagamento")
public class TipoPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoPagamento;

	@Column(name = "descricaoDoPagamento")
	private String descricaoPagamento;
}
