package br.com.springEstudo.AutorLivro.business.dto;


import java.util.UUID;


public record LivroPatchDto(String titulo, Integer anoPublicacao, String genero, UUID autorId) {

}
