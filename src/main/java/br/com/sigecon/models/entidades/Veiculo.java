package br.com.sigecon.models.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idEndereco")
	private Long idVeiculo;

	@Column(nullable = false, length = 8)
	private String placa;

	@Column(nullable = false, length = 20)
	private String marca;

	@Column(nullable = false, length = 20)
	private String modelo;

	@Column(nullable = false, length = 4)
	private int ano;

	@ManyToOne
	private Pessoa proprietarioVeiculo;

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Pessoa getProprietarioVeiculo() {
		return proprietarioVeiculo;
	}

	public void setProprietarioVeiculo(Pessoa proprietarioVeiculo) {
		this.proprietarioVeiculo = proprietarioVeiculo;
	}
}
