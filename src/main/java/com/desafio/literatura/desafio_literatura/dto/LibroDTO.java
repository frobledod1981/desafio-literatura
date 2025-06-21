package com.desafio.literatura.desafio_literatura.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import com.desafio.literatura.desafio_literatura.model.Libro;

public record LibroDTO(
        @NotBlank String titulo,
        @NotBlank String autor,
        @NotBlank String idioma,
        @NotNull Integer anioPublicacion,
        @NotNull Boolean autorVivo

) {
    public LibroDTO(Libro libro) {
        this(
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIdioma(),
                libro.getAnioPublicacion(),
                libro.getAutorVivo()
        );
    }

}