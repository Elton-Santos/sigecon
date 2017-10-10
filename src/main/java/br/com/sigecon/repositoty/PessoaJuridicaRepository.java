package br.com.sigecon.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigecon.models.entidades.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

	List<PessoaJuridica> findByCnpj(String cnpj);

	@Query("select e from PessoaJuridica e where nome like %?1% or ?1 is null")
	List<PessoaJuridica> porNome(String nome);
}
