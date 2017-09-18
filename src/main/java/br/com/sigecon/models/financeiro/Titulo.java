package br.com.sigecon.models.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sigecon.enuns.Situacao;
import br.com.sigecon.enuns.Tipo;
import br.com.sigecon.models.entidades.Unidade;

@Entity
@Table(name = "titulo")
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTitulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUnidade")
	private Unidade unidade;

	@Column(name = "dataDeEmissao")
	private Date dataEmissao;

	@Column(name = "dataDeValidade")
	private Date dataValidade;

	@Column(name = "dataDoPagamento")
	private Date dataPagamento;

	private BigDecimal valor;

	@Column(name = "ValorPago")
	private BigDecimal valorPago;

	@Column(name = "valorOriginal")
	private BigDecimal valorOriginal;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoPagamento")
	private TipoPagamento tipoDePagamento;

	public Situacao getSituacao() {
		return situacao;
	}

	public TipoPagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoPagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getIdTitulo() {
		return idTitulo;
	}

	public void setIdTitulo(Long idTitulo) {
		this.idTitulo = idTitulo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
}
