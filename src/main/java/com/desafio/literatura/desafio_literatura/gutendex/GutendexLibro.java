package com.desafio.literatura.desafio_literatura.gutendex;

import java.util.List;

// GutendexLibro.java
public record GutendexLibro(
        String title,
        List<AutorResponse> authors,
        List<String> languages
) {}

