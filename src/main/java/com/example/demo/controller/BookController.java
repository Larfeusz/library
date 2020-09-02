package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin
// to sprawia, że nasze api jest dostępne dla innych, udostępnia, ze można z innych serwerów z tego korzystać
@RestController
@RequestMapping("/api/")

public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping ("books")
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    @PostMapping("books")
    public Book createBook(@RequestParam String title,
                           @RequestParam String author){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public Book updateRepository (@PathVariable Integer id,  // PathVariable sprawia, że w sciezce mamy /books/{id}
                                  @RequestParam String title,
                                  @RequestParam String author){
        Book book = bookRepository.findById(id).get();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

