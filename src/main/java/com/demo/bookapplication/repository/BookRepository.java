package com.demo.bookapplication.repository;

import com.demo.bookapplication.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {
    List<BookEntity> findAllByName(String name);
    List<BookEntity> findAllByAuthor(String author);
    List<BookEntity> findAllByGenre(String genre);
    List<BookEntity> findAllByPrice(Double price);


}
