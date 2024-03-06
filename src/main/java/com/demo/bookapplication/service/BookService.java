package com.demo.bookapplication.service;

import com.demo.bookapplication.Exception.BookNotFoundException;
import com.demo.bookapplication.dto.BookDto;
import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Integer addBook(BookDto bookDto) {
        BookEntity bookEntity=bookRepository.save(BookEntity.builder().name(bookDto.getName()).author(bookDto.getAuthor()).price(bookDto.getPrice()).genre(bookDto.getGenre()).build());
        return bookEntity.getId();
    }

    public BookDto getBookById(Integer id) throws BookNotFoundException {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return BookDto.builder().name(bookEntity.getName()).author(bookEntity.getAuthor()).genre(bookEntity.getGenre()).price(bookEntity.getPrice()).build();
    }

    public BookDto updateBookById(Integer id, BookDto bookDto) throws BookNotFoundException {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setName(bookDto.getName());
        bookEntity.setGenre(bookDto.getGenre());
        bookEntity.setPrice(bookDto.getPrice());
        bookRepository.save(bookEntity);
        return getBookById(bookEntity.getId());
    }

    public BookDto deleteBookById(Integer id) throws BookNotFoundException {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        bookRepository.deleteById(id);
        return BookDto.builder().name(bookEntity.getName()).author(bookEntity.getAuthor()).genre(bookEntity.getGenre()).price(bookEntity.getPrice()).build();
    }


    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map((book)->BookDto.builder().name(book.getName()).author(book.getAuthor()).genre(book.getGenre()).price(book.getPrice()).build()).toList();
    }

    public List<BookDto> getBookByName(String name) {
        return bookRepository.findAllByName(name).stream().map((book)->BookDto.builder().name(book.getName()).author(book.getAuthor()).genre(book.getGenre()).price(book.getPrice()).build()).toList();
    }

    public List<BookDto> getBookByAuthor(String author) {
        return bookRepository.findAllByAuthor(author).stream().map((book)->BookDto.builder().name(book.getName()).author(book.getAuthor()).genre(book.getGenre()).price(book.getPrice()).build()).toList();
    }

    public List<BookDto> getBookByGenre(String genre) {
        return bookRepository.findAllByGenre(genre).stream().map((book)->BookDto.builder().name(book.getName()).author(book.getAuthor()).genre(book.getGenre()).price(book.getPrice()).build()).toList();
    }

    public List<BookDto> getBookByPrice(Double price) {
        return bookRepository.findAllByPrice(price).stream().map((book)->BookDto.builder().name(book.getName()).author(book.getAuthor()).genre(book.getGenre()).price(book.getPrice()).build()).toList();
    }

    public BookDto updateAuthor(Integer id, String author) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(new BookEntity());
        bookEntity.setAuthor(author);
        bookRepository.save(bookEntity);
        return getBookById(bookEntity.getId());
    }

    public BookDto updateGenre(Integer id, String genre) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(new BookEntity());
        bookEntity.setGenre(genre);
        bookRepository.save(bookEntity);
        return getBookById(bookEntity.getId());
    }

    public BookDto updatePrice(Integer id, Double price) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(new BookEntity());
        bookEntity.setPrice(price);
        bookRepository.save(bookEntity);
        return getBookById(bookEntity.getId());
    }

    public BookDto updateName(Integer id, String name) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(new BookEntity());
        bookEntity.setName(name);
        bookRepository.save(bookEntity);
        return getBookById(bookEntity.getId());
    }
}
