package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface AuthorService
{
    List<Author> findAll(Pageable pageable);

    void delete(long id);

    Author findAuthorById(long id);

    Author save(Author author);

    Author assign(long bookid, long authorid);
}
