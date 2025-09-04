package br.com.springEstudo.AutorLivro.business.dto;

import java.util.UUID;


public record LivroResponseDto(UUID id, String titulo, Integer anoPublicacao, String genero,
		AutorSumaryDto autor)

{

}
