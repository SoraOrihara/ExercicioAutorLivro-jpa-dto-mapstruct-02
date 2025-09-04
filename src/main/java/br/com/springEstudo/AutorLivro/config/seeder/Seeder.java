package br.com.springEstudo.AutorLivro.config.seeder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.springEstudo.AutorLivro.business.AutorService;
import br.com.springEstudo.AutorLivro.business.LivroService;
import br.com.springEstudo.AutorLivro.business.dto.AutorRequestDto;
import br.com.springEstudo.AutorLivro.business.dto.AutorResponseDto;
import br.com.springEstudo.AutorLivro.business.dto.LivroRequestDto;
import br.com.springEstudo.AutorLivro.infraestructure.repositories.AutorRepository;

@Component
public class Seeder implements CommandLineRunner {

	    private final AutorService autorService;
	    private final LivroService livroService;
	    private final AutorRepository autorRepository;
	    private final Random random = new Random();

	    public Seeder(AutorService autorService, LivroService livroService, AutorRepository autorRepository) {
	        this.autorService = autorService;
	        this.livroService = livroService;
	        this.autorRepository = autorRepository;
	    }

	    @Override
	    public void run(String... args) throws Exception {
	        if (autorRepository.count() == 0) {
	            System.out.println("Iniciando o seeder de dados...");

	            // --- 1. Criando Autores Famosos ---
	            // Criamos autores e armazenamos seus IDs em uma lista para facilitar a associação.
	            AutorResponseDto clarice = autorService.create(new AutorRequestDto("Clarice Lispector", LocalDate.of(1920, 12, 10)));
	            AutorResponseDto machado = autorService.create(new AutorRequestDto("Machado de Assis", LocalDate.of(1839, 6, 21)));
	            AutorResponseDto jorge = autorService.create(new AutorRequestDto("Jorge Amado", LocalDate.of(1912, 8, 10)));
	            
	            List<UUID> autoresIds = new ArrayList<>();
	            autoresIds.add(clarice.id());
	            autoresIds.add(machado.id());
	            autoresIds.add(jorge.id());
	            
	            System.out.println("Autores de exemplo criados com sucesso.");

	            // --- 2. Criando 50 Livros Fictícios com Loops ---
	            // Associamos cada livro a um autor aleatoriamente da lista acima.
	            for (int i = 1; i <= 50; i++) {
	                UUID autorAleatorioId = autoresIds.get(random.nextInt(autoresIds.size()));
	                
	                LivroRequestDto livroFicticio = new LivroRequestDto(
	                    "Livro Fictício " + i,
	                    1990 + random.nextInt(30), // Ano entre 1990 e 2019
	                    "Ficção",
	                    autorAleatorioId
	                );
	                livroService.create(livroFicticio);
	            }
	            
	            System.out.println("50 livros fictícios criados e associados com sucesso.");
	            
	            System.out.println("Seeder concluído. O banco de dados está populado.");
	        } else {
	            System.out.println("O banco de dados já contém dados. Seeder ignorado.");
	        }
	    }
	}
