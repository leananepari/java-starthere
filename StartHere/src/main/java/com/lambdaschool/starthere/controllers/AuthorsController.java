package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.services.AuthorService;
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
@RequestMapping(value = "/authors")
public class AuthorsController
{
    @Autowired
    private AuthorService authorService;

    // http://localhost:2019/authors/authors/?page=0&size=1
    // http://localhost:2019/authors/authors/?sort=lastname
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 10) Pageable pageable) throws ResourceNotFoundException
    {
//        logger.info("accessed");
        List<Author> authors = authorService.findAll(pageable);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> assignBookToAnAuthor(@PathVariable
                                                          long bookid, @PathVariable long authorid) throws ResourceNotFoundException
    {

        authorService.assign(bookid, authorid);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
