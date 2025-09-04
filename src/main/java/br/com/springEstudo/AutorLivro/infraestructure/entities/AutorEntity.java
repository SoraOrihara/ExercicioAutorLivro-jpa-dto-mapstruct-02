package br.com.springEstudo.AutorLivro.infraestructure.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_autor")
public class AutorEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(unique = true,nullable = false)
	private String nome;
	
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LivroEntity> livros = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<LivroEntity> getLivros() {
		return livros;
	}

	public void setLivros(Set<LivroEntity> livros) {
		this.livros = livros;
	}

	public UUID getId() {
		return id;
	}

	public AutorEntity() {
		super();
	}

	public AutorEntity(String nome, LocalDate dataNascimento, Set<LivroEntity> livros) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.livros = livros;
	}
	
	
	
	
}
