package br.com.springEstudo.AutorLivro.business.mapstructure;



import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.springEstudo.AutorLivro.business.dto.LivroPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroResponseDto;
import br.com.springEstudo.AutorLivro.infraestructure.entities.LivroEntity;

@Mapper(componentModel = "spring")
public interface LivroMapper {

	@Mapping(target="autor",ignore=true)
	LivroEntity paraLivroEntity(LivroRequestDto request);

	LivroResponseDto paraLivroResponseDto(LivroEntity entity);
	
	List<LivroResponseDto> paraListResposeDto(List<LivroEntity>livros);
	
	@Mapping(target="autor",ignore=true)
	void updateLivroPut(LivroRequestDto request,@MappingTarget LivroEntity entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target="autor",ignore=true)
	void updateLivroPatch(LivroPatchDto request,@MappingTarget LivroEntity entity);
	
}
