package br.com.attornatus.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.avaliacao.model.Endereco;
import br.com.attornatus.avaliacao.model.Pessoa;
import br.com.attornatus.avaliacao.repository.EnderecoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> getAll() {
		return ResponseEntity.ok(enderecoRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getById(@PathVariable Long id) {
		return enderecoRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<List<Endereco>> getByCep(@PathVariable String cep){

		return ResponseEntity.ok(enderecoRepository.findByCep(cep));
			
	}
	
	@PostMapping
	public ResponseEntity<Endereco> postEndereco(@Valid @RequestBody Endereco endereco) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
	}


	@PutMapping
	public ResponseEntity<Endereco> putEndereco(@Valid @RequestBody Endereco endereco) {
					
		return enderecoRepository.findById(endereco.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(enderecoRepository.save(endereco));
				})
				.orElse(ResponseEntity.notFound().build());

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEndereco(@PathVariable Long id) {
		
		return enderecoRepository.findById(id)
				.map(resposta -> {
					enderecoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
