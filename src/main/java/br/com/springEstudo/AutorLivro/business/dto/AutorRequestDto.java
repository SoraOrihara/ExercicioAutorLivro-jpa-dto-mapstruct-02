package br.com.springEstudo.AutorLivro.business.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDto(@NotBlank String nome,@NotBlank LocalDate dataNascimento) {

}
