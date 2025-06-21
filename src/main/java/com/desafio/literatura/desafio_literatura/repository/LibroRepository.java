package com.desafio.literatura.desafio_literatura.repository;

import com.desafio.literatura.desafio_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);

    List<Libro> findByAutorVivoTrueAndAnioPublicacionLessThanEqual(Integer anio);

    @Query("SELECT DISTINCT l.autor FROM Libro l")
    List<String> findDistinctAutores();

    Optional<Libro> findByTituloAndAutor(String titulo, String autor);
}