package br.com.sigecon.models.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sigecon.models.financeiro.Titulo;

@Entity
@Table(name = "unidade")
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUnidade")
	private Long idUnidade;

	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa proprietarioUnidade;

	@Column
	private BigDecimal valorCondominio;

	@OneToMany(mappedBy = "unidade", orphanRemoval = true)
	private List<Titulo> titulos = new ArrayList<>();

	public List<Titulo> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Pessoa getProprietarioUnidade() {
		return proprietarioUnidade;
	}

	public void setProprietarioUnidade(Pessoa proprietarioUnidade) {
		this.proprietarioUnidade = proprietarioUnidade;
	}

	public BigDecimal getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(BigDecimal valorCondominio) {
		this.valorCondominio = valorCondominio;
	}

}
