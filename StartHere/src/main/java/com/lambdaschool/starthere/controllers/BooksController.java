package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BooksController
{
    @Autowired
    private BookService bookService;

    // http://localhost:2019/books/books/?page=0&size=1
    // http://localhost:2019/books/books/?sort=booktitle
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 10) Pageable pageable) throws ResourceNotFoundException
    {
//        logger.info("accessed");
        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    // http://localhost:2019/books/data/book/1
    @PutMapping(value = "/data/book/{id}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long id) throws ResourceNotFoundException
    {

        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/data/books/{id} ")
    public ResponseEntity<?> deleteCourseById(@PathVariable long id) throws ResourceNotFoundException
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
