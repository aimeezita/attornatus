package br.com.attornatus.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.attornatus.avaliacao.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	
	public List<Endereco> findByCep(String cep);
	

	public List<Endereco> findAllById(@Param("id") Long id);
}
