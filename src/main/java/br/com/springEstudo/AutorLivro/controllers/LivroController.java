package br.com.springEstudo.AutorLivro.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springEstudo.AutorLivro.business.LivroService;
import br.com.springEstudo.AutorLivro.business.dto.LivroPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/biblioteca/livro")
public class LivroController {
private final LivroService livroService;
	
	public LivroController(LivroService livroService) {
		this.livroService=livroService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<LivroResponseDto> create(@RequestBody @Valid LivroRequestDto request){
		LivroResponseDto response= livroService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroResponseDto>> listarTudo(){
		return ResponseEntity.ok().body(livroService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroResponseDto> listarPorId(@PathVariable UUID id){
		return ResponseEntity.ok().body(livroService.listarPorId(id));
	}
	
	@PutMapping("/updatePut/{id}")
	public ResponseEntity<LivroResponseDto> updatePut(@PathVariable UUID id, @RequestBody @Valid LivroRequestDto request){
		return ResponseEntity.ok().body(livroService.updatePut(id, request));
	}
	
	@PatchMapping("/patch/{id}")
	public ResponseEntity<LivroResponseDto> updatePatch(@PathVariable UUID id, @RequestBody @Valid LivroPatchDto request){
		return ResponseEntity.ok().body(livroService.updatePatch(id, request));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable UUID id){
		livroService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
