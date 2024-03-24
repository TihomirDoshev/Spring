package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
    List<String> getAllAuthorsFirstAndLastNameForBooksBeforeYear1990();
}
