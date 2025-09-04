package br.com.springEstudo.AutorLivro.business.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record AutorResponseDto(UUID id,String nome,LocalDate dataNascimento, Set<UUID> livrosIds) {

}
