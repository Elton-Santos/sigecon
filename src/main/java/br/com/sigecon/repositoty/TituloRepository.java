package br.com.sigecon.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigecon.models.financeiro.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

}
