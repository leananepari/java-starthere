package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteBookFromWrote(id);
            bookRepository.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Book findBookById(long id) {
        return null;
    }

    @Override
    public Book save(Book book) {
        Book newBook = new Book();

        newBook.setBooktitle(book.getBooktitle());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());

        return bookRepository.save(newBook);
    }

    @Override
    public Book update(Book book, long id) {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getCopy() != 0)
        {
            currentBook.setCopy(book.getCopy());
        }
        if (book.getISBN() != null)
        {
            currentBook.setISBN(book.getISBN());
        }

        currentBook.setLastModifiedDate(currentBook.getLastModifiedDate());

        return bookRepository.save(currentBook);
    }
}
