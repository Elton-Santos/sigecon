package br.com.sigecon.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigecon.models.entidades.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

	List<PessoaFisica> findByNomeContainingIgnoreCase(String nome);

	List<PessoaFisica> findByCpf(String cpf);

	@Query("select e from PessoaFisica e where nome like %?1% or ?1 is null")
	List<PessoaFisica> porNome(String nome);

}
