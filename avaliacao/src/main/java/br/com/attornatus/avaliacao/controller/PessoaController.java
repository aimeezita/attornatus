package br.com.attornatus.avaliacao.controller;

import java.util.List;

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

import br.com.attornatus.avaliacao.model.Pessoa;
import br.com.attornatus.avaliacao.repository.EnderecoRepository;
import br.com.attornatus.avaliacao.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {

	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@GetMapping("/all")
	public ResponseEntity <List<Pessoa>> getAll(){
		
		return ResponseEntity.ok(pessoaRepository.findAll());
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
		return pessoaRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pessoa>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(pessoaRepository.findAllByNomeContainingIgnoreCase(nome));
			
	}
	
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Pessoa> postPessoa(@Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
	}
	
	
	



	@PutMapping("/atualizar")
	public ResponseEntity<Pessoa> putPessoa (@Valid @RequestBody Pessoa pessoa){
		
		return pessoaRepository.findById(pessoa.getId())
			.map(resposta -> ResponseEntity.ok().body(pessoaRepository.save(pessoa)))
			.orElse(ResponseEntity.notFound().build());
	}
	

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePessoa(@PathVariable Long id) {
		
			return pessoaRepository.findById(id)
			.map(resposta -> { 
				pessoaRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			})
			.orElse(ResponseEntity.notFound().build());
	
}
}
