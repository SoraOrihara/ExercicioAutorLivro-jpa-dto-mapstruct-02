package br.com.springEstudo.AutorLivro.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springEstudo.AutorLivro.infraestructure.entities.LivroEntity;
@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, UUID> {

}
