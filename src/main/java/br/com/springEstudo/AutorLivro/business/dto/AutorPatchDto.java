package br.com.springEstudo.AutorLivro.business.dto;

import java.time.LocalDate;

public record AutorPatchDto(String nome, LocalDate dataNascimento) {

}
