package br.com.springEstudo.AutorLivro.business;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.springEstudo.AutorLivro.business.dto.AutorPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorResponseDto;
import br.com.springEstudo.AutorLivro.business.mapstructure.AutorMapper;
import br.com.springEstudo.AutorLivro.exceptions.ResourceNotFoundException;
import br.com.springEstudo.AutorLivro.infraestructure.entities.AutorEntity;
import br.com.springEstudo.AutorLivro.infraestructure.repositories.AutorRepository;
import jakarta.transaction.Transactional;

@Service
public class AutorService {

	private final AutorRepository autorRepository;
	private final AutorMapper autorMapper;
	
	public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
		this.autorRepository=autorRepository;
		this.autorMapper=autorMapper;
	}
	
	public List<AutorResponseDto> listarTodos(){
		return autorMapper.paraListaResponse(autorRepository.findAll());
	}
	
	public AutorResponseDto listarPorId(UUID id) {
		return autorMapper.paraAutorResponseDto(autorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+id)));
	}
	
	@Transactional
	public void DeletarById(UUID id) {
		if(!autorRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o id "+id);
		}
		autorRepository.deleteById(id);
	}
	@Transactional
	public AutorResponseDto create(AutorRequestDto request) {
		AutorEntity autorNovo = autorMapper.paraAutorEntity(request);
		autorRepository.save(autorNovo);
		return autorMapper.paraAutorResponseDto(autorNovo);
	}
	
	@Transactional
	public AutorResponseDto updatePut(UUID id,AutorRequestDto request) {
		AutorEntity autorExistente = autorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+id));
		autorMapper.updateParaAutorRequest(request, autorExistente);
		return autorMapper.paraAutorResponseDto(autorExistente);
	}
	
	@Transactional
	public AutorResponseDto updatePatch(UUID id,AutorPatchDto request) {
		AutorEntity autorExistete =autorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+id));
		autorMapper.updateParaAutorPatch(request, autorExistete);
		return autorMapper.paraAutorResponseDto(autorExistete);
	}
	
}
