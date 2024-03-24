package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Set<Book>findAllByReleaseDateAfter(LocalDate date);
}
