package br.com.attornatus.avaliacao.model;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name ="tb_pesssoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	
	
	@NotNull(message = "O atributo nome é obrigatório")
	private String nome;
	
	@NotNull(message = "O atributo data de nascimento é obrigatório")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate nascimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="endereco_id")
	@JsonIgnoreProperties("pessoa")
	private Endereco endereco;


	
	//Método Construtor  - Com os atributos
	
	//verificar se endereço deve estar aí, pois o endereço é inserido depois segundo a regra de negócio
	
	public Pessoa(Long id, String nome, LocalDate nascimento, Endereco endereco) {
	
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
	}


	//Construtor  - Sem atributos
	public Pessoa() { };
	



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getNascimento() {
		return nascimento;
	}


	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
		
}
