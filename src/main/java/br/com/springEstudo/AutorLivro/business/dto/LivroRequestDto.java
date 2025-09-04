package br.com.springEstudo.AutorLivro.business.dto;


import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record LivroRequestDto(
		@NotBlank(message = "O titulo do livro é obrigatorio!") 
		String titulo,

		@NotBlank(message = "O ano de publicação é obrigatorio!") 
		Integer anoPublicacao,

		@NotBlank(message = "O genero é obrigatorio!") 
		String genero,
		
		@NotBlank(message = "O id do autor é obrigatorio") 
		UUID autorId

) {

}
