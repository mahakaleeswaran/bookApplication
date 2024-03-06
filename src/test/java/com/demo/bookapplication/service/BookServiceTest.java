package com.demo.bookapplication.service;

import com.demo.bookapplication.Exception.BookNotFoundException;
import com.demo.bookapplication.dto.BookDto;
import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
    private final BookDto bookDto = BookDto.builder().name("harry potter").author("j k Rowling").genre("fantasy").price(200.0).build();

    private final BookEntity bookEntity = BookEntity.builder().id(1).name("harry potter").author("j k Rowling").genre("fantasy").price(200.0).build();


    @Test
    void addBookTest() {
        Mockito.when(bookRepository.save(any())).thenReturn(bookEntity);
        Assertions.assertEquals(1, bookService.addBook(bookDto));
    }

    @Test
    void getBookByIdNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
    }


    @Test
    void getBookByIdTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(bookEntity));
        Assertions.assertEquals(bookDto, bookService.getBookById(1));
    }

    @Test
    void updateBookByIdNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updateBookById(1, bookDto));
    }


    @Test
    void updateBookByIdTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(bookEntity));
        Assertions.assertEquals(bookDto, bookService.updateBookById(1, bookDto));
    }

    @Test
    void deleteBookByIdNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(1));
    }


    @Test
    void deleteBookByIdTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(1));
        Mockito.verify(bookRepository, Mockito.never()).deleteById(any());
    }


    @Test
    void getAllBooksTest() {
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        List<BookDto> response = bookService.getAllBooks();
        Assertions.assertEquals(response,bookService.getAllBooks());
    }

    @Test
    void getBookByNameTest(){
        Mockito.when(bookRepository.findAllByName(any())).thenReturn(List.of(bookEntity));
        List<BookDto> response = bookService.getBookByName("name");
        Assertions.assertEquals(response,bookService.getBookByName("name"));
    }

    @Test
    void getBookByAuthorTest(){
        Mockito.when(bookRepository.findAllByAuthor(any())).thenReturn(List.of(bookEntity));
        List<BookDto> response = bookService.getBookByAuthor("name");
        Assertions.assertEquals(response,bookService.getBookByAuthor("name"));
    }

    @Test
    void getBookByGenreTest(){
        Mockito.when(bookRepository.findAllByGenre(any())).thenReturn(List.of(bookEntity));
        List<BookDto> response = bookService.getBookByGenre("name");
        Assertions.assertEquals(response,bookService.getBookByGenre("name"));
    }

    @Test
    void getBookByPriceTest(){
        Mockito.when(bookRepository.findAllByPrice(any())).thenReturn(List.of(bookEntity));
        List<BookDto> response = bookService.getBookByPrice(200.0);
        Assertions.assertEquals(response,bookService.getBookByPrice(200.0));
    }

    @Test
    void updateNameTest(){
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(bookEntity));
        BookDto bookDto=bookService.updateName(1,"harry");
        Assertions.assertEquals(bookDto,bookService.updateName(1,"harry"));
    }

    @Test
    void updateAuthorNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updateAuthor(1, "New Author"));
    }


    @Test
    void updateAuthorTest(){
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(bookEntity));
        BookDto bookDto=bookService.updateAuthor(1,"harry");
        Assertions.assertEquals(bookDto,bookService.updateAuthor(1,"harry"));
    }

    @Test
    void updateGenreNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updateGenre(1, "New Genre"));
    }

    @Test
    void updateGenreTest(){
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(bookEntity));
        BookDto bookDto=bookService.updateGenre(1,"harry");
        Assertions.assertEquals(bookDto,bookService.updateGenre(1,"harry"));
    }

    @Test
    void updatePriceNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updatePrice(1, 0.0));
    }

    @Test
    void updatePriceTest(){
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(bookEntity));
        BookDto bookDto=bookService.updatePrice(1,200.0);
        Assertions.assertEquals(bookDto,bookService.updatePrice(1,200.0));
    }
}
