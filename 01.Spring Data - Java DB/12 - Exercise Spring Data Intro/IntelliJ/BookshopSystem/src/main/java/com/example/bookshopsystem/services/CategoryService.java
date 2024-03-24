package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Category;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public interface CategoryService {
void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
