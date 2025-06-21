package com.desafio.literatura.desafio_literatura.controller;

import com.desafio.literatura.desafio_literatura.dto.LibroDTO;
import com.desafio.literatura.desafio_literatura.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    // 🟢 Listar todos los libros
    @GetMapping
    public List<LibroDTO> listarTodos() {
        return servicio.listarTodos();
    }

    // 🔎 Buscar libros por título (filtro parcial)
    @GetMapping("/buscar")
    public List<LibroDTO> buscarPorTitulo(@RequestParam String titulo) {
        return servicio.buscarPorTitulo(titulo);
    }

    // 🌐 Listar libros por idioma
    @GetMapping("/idioma/{idioma}")
    public List<LibroDTO> listarPorIdioma(@PathVariable String idioma) {
        return servicio.listarPorIdioma(idioma);
    }

    // ✍️ Listar autores únicos
    @GetMapping("/autores")
    public List<String> listarAutores() {
        return servicio.listarAutores();
    }

    // 👴 Listar autores vivos hasta cierto año
    @GetMapping("/autores-vivos")
    public List<String> autoresVivosEnAnio(@RequestParam Integer anio) {
        return servicio.listarNombresAutoresVivosEnAnio(anio);
    }

    // 📝 Registrar nuevo libro
    @PostMapping
    public ResponseEntity<LibroDTO> registrarLibro(@RequestBody @Valid LibroDTO dto) {
        LibroDTO guardado = servicio.guardarLibroDesdeDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // 🌐 Importar libro desde fuente externa (como Gutendex)
    @PostMapping("/importar")
    public ResponseEntity<LibroDTO> importarLibro(@RequestBody @Valid LibroDTO dto) {
        LibroDTO guardado = servicio.guardarLibroDesdeDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
}
