package br.com.attornatus.avaliacao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.attornatus.avaliacao.model.Endereco;
import br.com.attornatus.avaliacao.model.Pessoa;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PessoaRepositoryTest {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@BeforeAll
	void start(){
		
		    LocalDate nascimentoJoao = LocalDate.of(1, 1, 1990);
		    Endereco enderecoJoao = new Endereco(0L,"Rua dos Guararapes", "290000", 235, "Serra");
		    Pessoa joao = new Pessoa(0L, "João Silva",nascimentoJoao, enderecoJoao);
		  
		    pessoaRepository.save(joao);
		  
		    LocalDate nascimentoPatricia = LocalDate.of(12, 2, 1993);
		    Endereco enderecoPatricia = new Endereco(0L,"Rua da Alegria", "0490000", 700, "Instância");
		    Pessoa patricia = new Pessoa(0L, "Patricia Silva", nascimentoPatricia, enderecoPatricia);
			
		    pessoaRepository.save(patricia);
		    
		    LocalDate nascimentoNicolas = LocalDate.of(21, 2, 2020);
		    Endereco enderecoNicolas = new Endereco(0L, "Rua dos Abacateiros", "210000000", 123, "Joao Pessoa");
		    Pessoa nicolas = new Pessoa(0L, "Nicolas Silva", nascimentoNicolas, enderecoNicolas);
		    
		    pessoaRepository.save(nicolas);
	}
	
	
	
	@Test
	@DisplayName("Retorna 3 pessoas")
	public void retornaTresPessoas() {
		List<Pessoa> listaDePessoas = pessoaRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDePessoas.size());
		assertTrue(listaDePessoas.get(0).getNome().equals("João Silva"));
		assertTrue(listaDePessoas.get(1).getNome().equals("Patricia Silva"));
		assertTrue(listaDePessoas.get(2).getNome().equals("Nicolas Silva"));
	}
	
	
}