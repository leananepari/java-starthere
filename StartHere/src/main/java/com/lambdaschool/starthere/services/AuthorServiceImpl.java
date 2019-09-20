package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> findAll(Pageable pageable) {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Author findAuthorById(long id) {
        return null;
    }

    @Override
    public Author save(Author author) {
        Author newAuthor = new Author();

        newAuthor.setFirstname(author.getFirstname());
        newAuthor.setLastname(author.getLastname());

        return authorRepository.save(newAuthor);
    }

    @Override
    public Author assign(long bookid, long authorid) {
        Author currentAuthor = authorRepository.findById(authorid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(authorid)));
        Book currentBook = bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(bookid)));
        List<Book> bookList = currentAuthor.getBooks();
        bookList.add(currentBook);
        currentAuthor.setBooks(bookList);

        return authorRepository.save(currentAuthor);
    }
}
