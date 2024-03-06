package com.demo.bookapplication.controller;
import com.demo.bookapplication.dto.BookDto;
import com.demo.bookapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/add")
    public Integer addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(bookDto);
    }

    @GetMapping("/getById/{id}")
    public BookDto getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);

    }

    @GetMapping("/getByName/{name}")
    public List<BookDto> getBookByName(@PathVariable String name){
        return bookService.getBookByName(name);
    }

    @GetMapping("/getByAuthor/{author}")
    public List<BookDto> getBookByAuthor(@PathVariable String author){
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("/getByGenre/{genre}")
    public List<BookDto> getBookByGenre(@PathVariable String genre){
        return bookService.getBookByGenre(genre);
    }

    @GetMapping("/getByPrice/{price}")
    public List<BookDto> getBookByPrice(@PathVariable Double price){
        return bookService.getBookByPrice(price);
    }

    @GetMapping("/getAll")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/update/{id}")
    public BookDto updateBookById(@PathVariable Integer id, @RequestBody BookDto bookDto){
        return bookService.updateBookById(id,bookDto);
    }

    @PatchMapping("/updateName/{id}")
    public BookDto updateBookName(@PathVariable Integer id,@RequestBody String name){
        String trimmedName = name.trim();
        return bookService.updateName(id,trimmedName);
    }

    @PatchMapping("/updateAuthor/{id}")
    public BookDto updateBookAuthor(@PathVariable Integer id,@RequestBody String author){
        String trimmedAuthor = author.trim();
        return bookService.updateAuthor(id, trimmedAuthor);
    }

    @PatchMapping("/updateGenre/{id}")
    public BookDto updateBookGenre(@PathVariable Integer id,@RequestBody String genre){
        String trimmedGenre = genre.trim();
        return bookService.updateGenre(id,trimmedGenre);
    }

    @PatchMapping("/updatePrice/{id}")
    public BookDto updateBookPrice(@PathVariable Integer id,@RequestBody Double price){
        return bookService.updatePrice(id,price);
    }

    @DeleteMapping("/delete/{id}")
    public BookDto deleteBookById(@PathVariable Integer id){
        return bookService.deleteBookById(id);
    }

}
