package com.sycon.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sycon.model.Condominio;
import com.sycon.model.Conta;
import com.sycon.model.ContaAPagar;
import com.sycon.model.ContaAReceber;
import com.sycon.model.Unidade;
import com.sycon.repository.ContaAReceberRepository;
import com.sycon.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private ContaAReceberService serviceCR;

	@Autowired
	private ContaAPagarService serviceCP;

	public List<Conta> findAll() {
		return repository.findAll();
	}

	public Conta findOne(Long id) {
		return repository.findOne(id);
	}

	public Conta saveAndFlush(Conta conta) {
		return repository.saveAndFlush(conta);
	}

	public Conta save(Conta conta) {
		return repository.save(conta);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}

	public List<Conta> pesquisa(String descricaoConta, Condominio condominio) {

		return repository.findByDescricaoContaOrCondominioConta(descricaoConta, condominio);
	}

	public void gerarRateio(List<Conta> contas, Date calStart, Date calEnd) {
		contas = calcularTotalDespesas(contas, calStart, calEnd);
		double totalDespesasCondominio = 0.0;
		String descricaoDespesas = "";
		List<Unidade> listaUnidades = contas.get(0).getCondominioConta().getUnidades();
		for (Conta conta : contas) {
			totalDespesasCondominio += conta.getTotalDespesas();
			descricaoDespesas += conta.getDescricaoConta() + " | Total de Despesas: " + conta.getTotalDespesas()
					+ " | ";
		}
		for (Unidade unidade : listaUnidades) {
			ContaAReceber cr = new ContaAReceber();
			cr.setUnidadeCR(unidade);
			cr.setValorCR(totalDespesasCondominio / listaUnidades.size());
			cr.setDescricaoCR(descricaoDespesas);
			cr.setNomeCR(
					"Rateio Condominio " + unidade.getNomeUnidade() + " " + unidade.getProprietarioUnidade().getNome());
			// Necessário dizer qual a data de vencimento da receita no ato da geração do
			// rateio.
			serviceCR.save(cr);
		}
		System.out.println("Rateio Gerado com sucesso!");

	}

	public List<Conta> calcularTotalDespesas(List<Conta> listaDeContas) {
		Double totalDespesas;
		List<Conta> listaContas = listaDeContas;
		for (int x = 0; x < listaContas.size(); x++) {
			totalDespesas = 0.0;
			List<ContaAPagar> listaContasPagar = serviceCP.findByContaCP(listaContas.get(x));
			for (int i = 0; i < listaContasPagar.size(); i++) {
				totalDespesas += listaContasPagar.get(i).getValorCP();
			}
			listaContas.get(x).setTotalDespesas(totalDespesas);
			;
		}
		return listaContas;
	}

	public List<Conta> calcularTotalDespesas(List<Conta> listaDeContas, Date calStart, Date calEnd) {
		Double totalDespesas;
		List<Conta> listaContas = listaDeContas;
		for (int x = 0; x < listaContas.size(); x++) {
			System.out.println(listaContas.size());
			totalDespesas = 0.0;
			List<ContaAPagar> listaContasPagar = serviceCP.findByContaCPAndDtPagamentoCPBetween(listaContas.get(x),
					calStart, calEnd);
			if (listaContasPagar.size() > 0) {
				for (int i = 0; i < listaContasPagar.size(); i++) {
					totalDespesas += listaContasPagar.get(i).getValorCP();
				}
				listaContas.get(x).setTotalDespesas(totalDespesas);
			} else {
				listaContas.get(x).setTotalDespesas(0.0);
			}
		}
		return listaContas;
	}

}
