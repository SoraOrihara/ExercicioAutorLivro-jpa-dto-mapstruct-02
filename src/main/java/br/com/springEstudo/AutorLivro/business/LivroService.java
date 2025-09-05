package br.com.springEstudo.AutorLivro.business;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.springEstudo.AutorLivro.business.dto.LivroPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroResponseDto;
import br.com.springEstudo.AutorLivro.business.mapstructure.LivroMapper;
import br.com.springEstudo.AutorLivro.exceptions.ResourceNotFoundException;
import br.com.springEstudo.AutorLivro.infraestructure.entities.AutorEntity;
import br.com.springEstudo.AutorLivro.infraestructure.entities.LivroEntity;
import br.com.springEstudo.AutorLivro.infraestructure.repositories.AutorRepository;
import br.com.springEstudo.AutorLivro.infraestructure.repositories.LivroRepository;
import jakarta.transaction.Transactional;

@Service
public class LivroService {

	private final LivroRepository livroRepository;
	private final LivroMapper livroMapper;
	private final AutorRepository autorRepository;
	
	public LivroService(LivroRepository livroRepository,LivroMapper livroMapper,AutorRepository autorRepository) {
		this.livroRepository=livroRepository;
		this.livroMapper=livroMapper;
		this.autorRepository=autorRepository;
	}
	
	
	public List<LivroResponseDto> listarTodos() {
		return livroMapper.paraListResposeDto(livroRepository.findAll());
	}
	
	public LivroResponseDto listarPorId(UUID id) {
		return livroMapper.paraLivroResponseDto(livroRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+id)));
	}
	
	@Transactional
	public void deleteById(UUID id) {
		if(!livroRepository.existsById(id)){
			throw new ResourceNotFoundException("Não foi possivel encontrar o id "+id);
		}
		livroRepository.deleteById(id);
	}
	
	@Transactional
	public LivroResponseDto create(LivroRequestDto request) {
		
		LivroEntity livroNovo=livroMapper.paraLivroEntity(request);
		AutorEntity autor= autorRepository.findById(request.autorId()).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+request.autorId()));
		
		livroNovo.setAutor(autor);
		LivroEntity livroSalvo = livroRepository.save(livroNovo);
		return livroMapper.paraLivroResponseDto(livroSalvo);
	}
	
	@Transactional
	public LivroResponseDto updatePut(UUID id, LivroRequestDto request) {
		LivroEntity livroExistente = livroRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+id));
		livroMapper.updateLivroPut(request, livroExistente);
		AutorEntity novoAutor= autorRepository.findById(request.autorId()).orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar o id "+request.autorId()));
		livroExistente.setAutor(novoAutor);
		LivroEntity livroSalvo=livroRepository.save(livroExistente);
		return livroMapper.paraLivroResponseDto(livroSalvo);
	}
	
	@Transactional
	public LivroResponseDto updatePatch(UUID id, LivroPatchDto request) {
		LivroEntity livroExistente = livroRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Não foi possivel encontrar o id "+id));
		livroMapper.updateLivroPatch(request, livroExistente);
		if (request.autorId() != null) {
	        // Passo 2: Se foi, busca o autor
	        AutorEntity novoAutor = autorRepository.findById(request.autorId())
	            .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado."));
	        
	        // Passo 3: Seta o novo autor na entidade Livro
	        livroExistente.setAutor(novoAutor);
	    }
		LivroEntity livroSalvo =livroRepository.save(livroExistente);		
		return livroMapper.paraLivroResponseDto(livroSalvo);
	}
}
