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

import br.com.springEstudo.AutorLivro.business.AutorService;
import br.com.springEstudo.AutorLivro.business.dto.AutorPatchDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorResponseDto;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/biblioteca/autor")
public class AutorController {

	private final AutorService autorService;
	
	public AutorController(AutorService autorService) {
		this.autorService=autorService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<AutorResponseDto> create(@RequestBody @Valid AutorRequestDto request){
		AutorResponseDto response= autorService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<AutorResponseDto>> listarTudo(){
		return ResponseEntity.ok().body(autorService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorResponseDto> listarPorId(@PathVariable UUID id){
		return ResponseEntity.ok().body(autorService.listarPorId(id));
	}
	
	@PutMapping("/updatePut/{id}")
	public ResponseEntity<AutorResponseDto> updatePut(@PathVariable UUID id, @RequestBody @Valid AutorRequestDto request){
		return ResponseEntity.ok().body(autorService.updatePut(id, request));
	}
	
	@PatchMapping("/patch/{id}")
	public ResponseEntity<AutorResponseDto> updatePatch(@PathVariable UUID id, @RequestBody @Valid AutorPatchDto request){
		return ResponseEntity.ok().body(autorService.updatePatch(id, request));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable UUID id){
		autorService.DeletarById(id);
		return ResponseEntity.ok().build();
	}
}
