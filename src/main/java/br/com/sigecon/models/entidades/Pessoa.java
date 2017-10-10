package br.com.sigecon.models.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.sigecon.enuns.TipoPessoa;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpessoa")
	private Long idPessoa;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_contato")
	private Contato contato;

	@Column(name = "sexo")
	private String sexo;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(name = "dataCadastro")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dataCadastro;

	@Column
	private boolean ativo = true;

	public Pessoa() {

	}

	public Pessoa(Long idPessoa, Endereco endereco, TipoPessoa tipoPessoa, Contato contato, String sexo,
			Date dataCadastro, boolean ativo) {
		this.idPessoa = idPessoa;
		this.endereco = endereco;
		this.tipoPessoa = tipoPessoa;
		this.contato = contato;
		this.sexo = sexo;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
