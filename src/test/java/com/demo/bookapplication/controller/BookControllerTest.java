package com.demo.bookapplication.controller;

import com.demo.bookapplication.Exception.BookNotFoundException;
import com.demo.bookapplication.dto.BookDto;
import com.demo.bookapplication.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private final BookDto bookDto = BookDto.builder().name("harry potter").author("j k Rowling").genre("fantasy").price(200.0).build();

    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;

    @Test
     void addBookTest(){
        Mockito.when(bookService.addBook(any())).thenReturn(1);
        Assertions.assertEquals(1, bookController.addBook(bookDto));
     }

    @Test
    void getBookByIdNotFoundTest() {
        Mockito.when(bookService.getBookById(1)).thenThrow(new BookNotFoundException("Book not found with id: 1"));
        Assertions.assertThrows(BookNotFoundException.class, () -> bookController.getBookById(1));
    }


    @Test
    void getBookByIdTest(){
         Mockito.when(bookService.getBookById(any())).thenReturn(bookDto);
         Assertions.assertEquals(bookDto, bookController.getBookById(1));
     }

    @Test
    void getBookByNameTest(){
        Mockito.when(bookService.getBookByName(any())).thenReturn(List.of(bookDto));
        Assertions.assertEquals(List.of(bookDto), bookController.getBookByName(any()));
    }

    @Test
    void getBookByAuthorTest(){
        Mockito.when(bookService.getBookByAuthor(any())).thenReturn(List.of(bookDto));
        Assertions.assertEquals(List.of(bookDto), bookController.getBookByAuthor(any()));
    }

    @Test
    void getBookByGenreTest(){
        Mockito.when(bookService.getBookByGenre(any())).thenReturn(List.of(bookDto));
        Assertions.assertEquals(List.of(bookDto), bookController.getBookByGenre(any()));
    }

    @Test
    void getBookByPriceTest(){
        Mockito.when(bookService.getBookByPrice(any())).thenReturn(List.of(bookDto));
        Assertions.assertEquals(List.of(bookDto), bookController.getBookByPrice(any()));
    }
    @Test
    void getAllBooksTest(){
        Mockito.when(bookService.getAllBooks()).thenReturn(List.of(bookDto));
        Assertions.assertEquals(List.of(bookDto), bookController.getAllBooks());
    }

    @Test
    void updateBookByIdNotFoundTest() {
        Mockito.when(bookService.updateBookById(1, bookDto)).thenThrow(new BookNotFoundException("Book not found with id: 1"));
        Assertions.assertThrows(BookNotFoundException.class, () -> bookController.updateBookById(1, bookDto));
    }


    @Test
    void updateBookByIdTest(){
        Mockito.when(bookService.updateBookById(any(),any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.updateBookById(any(),any()));
    }



    @Test
    void updateBookNameTest(){
        Mockito.when(bookService.updateName(any(),any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.updateBookName(1,"harry potter"));
    }

    @Test
    void updateBookAuthorTest(){
        Mockito.when(bookService.updateAuthor(any(),any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.updateBookAuthor(1,"j k rowling"));
    }

    @Test
    void updateBookGenreTest(){
        Mockito.when(bookService.updateGenre(any(),any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.updateBookGenre(1,"fantasy"));
    }

    @Test
    void updateBookPriceTest(){
        Mockito.when(bookService.updatePrice(any(),any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.updateBookPrice(1,200.0));
    }

    @Test
    void deleteBookByIdNotFoundTest() {
        Mockito.when(bookService.deleteBookById(1)).thenThrow(new BookNotFoundException("Book not found with id: 1"));
        Assertions.assertThrows(BookNotFoundException.class, () -> bookController.deleteBookById(1));
    }


    @Test
    void deleteBookByIdTest(){
        Mockito.when(bookService.deleteBookById(any())).thenReturn(bookDto);
        Assertions.assertEquals(bookDto,bookController.deleteBookById(any()));
    }


}
