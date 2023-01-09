package br.com.attornatus.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.attornatus.avaliacao.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	
	
	public List<Pessoa> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);



}
