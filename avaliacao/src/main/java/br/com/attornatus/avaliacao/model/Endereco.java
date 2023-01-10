package br.com.attornatus.avaliacao.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_endereco")
public class Endereco {

	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message = "O atributo Logradouro é obrigatório")
	private String logradouro;
	
	@NotNull(message = "O atributo CEP é obrigatório")
	private String cep;
	
	private int numero;
	
	@NotNull(message = "O atributo Cidade é obrigatório")
	private String cidade;
	
	@OneToOne(mappedBy = "endereco")
	@JsonIgnoreProperties("endereco")
	private Pessoa pessoa;
	
	public Endereco() {}
			
	public Endereco(Long id, String logradouro, int numero,	String cidade,String cep) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	


	
	
	
	

	
}
