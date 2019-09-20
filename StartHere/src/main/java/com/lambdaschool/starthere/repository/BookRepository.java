package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
    @Modifying
    @Query(value = "DELETE FROM wrote WHERE bookid = :bookid", nativeQuery = true)
    void deleteBookFromWrote(long bookid);
}
