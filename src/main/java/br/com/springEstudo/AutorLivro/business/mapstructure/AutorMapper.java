package br.com.springEstudo.AutorLivro.business.mapstructure;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.springEstudo.AutorLivro.business.dto.AutorPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorResponseDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorSumaryDto;
import br.com.springEstudo.AutorLivro.infraestructure.entities.AutorEntity;
import br.com.springEstudo.AutorLivro.infraestructure.entities.LivroEntity;

@Mapper(componentModel = "spring")
public interface AutorMapper {

	@Mapping(target="livros",ignore=true)
	AutorEntity paraAutorEntity(AutorRequestDto request);
	
	@Mapping(source="livros",target="livrosIds")
	AutorResponseDto paraAutorResponseDto(AutorEntity entity);
	
	 default Set<UUID> paraSetUUID (Set<LivroEntity>livros){
		 if(livros.isEmpty()) {
			 return null;
		 }
		 return livros.stream().map(LivroEntity::getId).collect(Collectors.toSet());
	 }
	 
	 @Mapping(target = "livros", ignore = true)
	 void updateParaAutorRequest(AutorRequestDto request,@MappingTarget AutorEntity entity);
	 
	 @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	 @Mapping(target = "livros", ignore = true)
	 void updateParaAutorPatch(AutorPatchDto request,@MappingTarget AutorEntity entity);
	 
	 AutorSumaryDto paraAutorSumary(AutorEntity entity);
	
}
