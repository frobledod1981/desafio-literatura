package com.desafio.literatura.desafio_literatura.service;

import com.desafio.literatura.desafio_literatura.dto.LibroDTO;
import com.desafio.literatura.desafio_literatura.gutendex.GutendexResponse;
import com.desafio.literatura.desafio_literatura.model.Libro;
import com.desafio.literatura.desafio_literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository repositorio;

    @Autowired
    private RestTemplate restTemplate;

    public LibroService(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    /** Guarda un nuevo libro, validando que no exista uno con mismo título y autor */
    @Transactional
    public LibroDTO guardarLibroDesdeDTO(LibroDTO dto) {
        Optional<Libro> existente = repositorio.findByTituloAndAutor(dto.titulo(), dto.autor());

        if (existente.isPresent()) {
            throw new DataIntegrityViolationException("El libro ya existe.");
        }

        Libro libro = new Libro();
        libro.setTitulo(dto.titulo());
        libro.setAutor(dto.autor());
        libro.setIdioma(dto.idioma());
        libro.setAnioPublicacion(dto.anioPublicacion());
        libro.setAutorVivo(dto.autorVivo());

        return new LibroDTO(repositorio.save(libro));
    }

    /** Devuelve todos los libros registrados */
    @Transactional(readOnly = true)
    public List<LibroDTO> listarTodos() {
        return repositorio.findAll()
                .stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    /** Devuelve libros cuyo título contiene el término indicado (sin distinción entre mayúsculas/minúsculas) */
//    @Transactional(readOnly = true)
//    public List<LibroDTO> buscarPorTitulo(String titulo) {
//        return repositorio.findByTituloContainingIgnoreCase(titulo)
//                .stream()
//                .map(LibroDTO::new)
//                .collect(Collectors.toList());
//    }
    public List<LibroDTO> buscarPorTitulo(String titulo) {
        List<Libro> encontrados = repositorio.findByTituloContainingIgnoreCase(titulo);
        System.out.println("📘 Encontrados: " + encontrados.size());
        return encontrados.stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }


    /** Devuelve libros por idioma */
    @Transactional(readOnly = true)
    public List<LibroDTO> listarPorIdioma(String idioma) {
        return repositorio.findByIdiomaIgnoreCase(idioma)
                .stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    /** Lista los nombres de autores únicos registrados */
    @Transactional(readOnly = true)
    public List<String> listarAutores() {
        return repositorio.findDistinctAutores();
    }

    /** Devuelve autores vivos en o antes del año especificado */
    @Transactional(readOnly = true)
    public List<String> listarNombresAutoresVivosEnAnio(Integer anio)
    {
        return repositorio.findByAutorVivoTrueAndAnioPublicacionLessThanEqual(anio)
                .stream()
                .map(Libro::getAutor)
                .distinct()
                .collect(Collectors.toList());
    }

    /** Busca libros en Gutendex que estén en español */
    public List<LibroDTO> buscarEnGutendex(String termino) {
        String url = "https://gutendex.com/books/?search=" + termino;
        GutendexResponse respuesta = restTemplate.getForObject(url, GutendexResponse.class);

        if (respuesta == null || respuesta.results() == null) {
            return Collections.emptyList();
        }

        return respuesta.results().stream()
                .filter(libro -> libro.languages() != null && libro.languages().contains("es"))
                .map(libro -> new LibroDTO(
                        libro.title() != null ? libro.title() : "Título desconocido",
                        libro.authors() != null && !libro.authors().isEmpty()
                                ? libro.authors().get(0).name()
                                : "Autor desconocido",
                        "es",
                        0,
                        false
                ))
                .collect(Collectors.toList());
    }
}