package br.com.attornatus.avaliacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.attornatus.avaliacao.model.Endereco;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnderecoControllerTest {

	/**
	 * Injeta um objeto da Classe TestRestTemplate, responsável por fazer requisições HTTP (semelhante ao Postman)
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private EnderecoController enderecoController;
	
	
		
	@Test
	@Order(1)
	@DisplayName("Cadastrar um Endereco")
	public void cadastraPessoa() {
		HttpEntity<Endereco> requisicao = new HttpEntity<Endereco>(new Endereco(0L,"Rua João Avelar", 149, "Oitizeiro", "078123555"));
		ResponseEntity<Endereco> resposta = testRestTemplate
				.exchange("/endereco", HttpMethod.POST, requisicao, Endereco.class);
		
		
		/**
		 * Verifica se a requisição retornou o Status Code CREATED (201) 
		 * Se for verdadeira, o teste passa, se não, o teste falha.
		 */
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		
			
		
	}
	
	
	@Test
	@Order(2)
	@DisplayName("Busca Enderecos Cadastrados")
	public void buscaEnderecos() {
		enderecoController.postEndereco(new Endereco(0L,"Rua Professor Dumbledore", 200, "Lummus", "078123555"));
		enderecoController.postEndereco(new Endereco(0L,"Rua Ordem da Fenix", 80, "Patrono", "123321111"));
		enderecoController.postEndereco(new Endereco(0L," Rua dos Alfeneiros", 4,  "Surrey", "77254635"));
		
		ResponseEntity<String>resposta = testRestTemplate.exchange("/endereco", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}
}