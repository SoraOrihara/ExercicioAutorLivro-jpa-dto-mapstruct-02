package br.com.springEstudo.AutorLivro.infraestructure.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_livro")
public class LivroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String titulo;

	private Integer anoPublicacao;

	@Column(nullable = false)
	private String genero;

	@ManyToOne
	@JoinColumn(name = "autor_Id")
	private AutorEntity autor;

	public UUID getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public AutorEntity getAutor() {
		return autor;
	}

	public void setAutor(AutorEntity autor) {
		this.autor = autor;
	}

	public LivroEntity() {
		super();
	}

	public LivroEntity(String titulo, Integer anoPublicacao, String genero, AutorEntity autor) {
		super();
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
		this.autor = autor;
	}

	
	
	
}
