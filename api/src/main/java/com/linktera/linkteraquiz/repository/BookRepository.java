package com.linktera.linkteraquiz.repository;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends BaseRepository<Book> {
     Optional<Book> findByBookId(UUID bookId);
     Optional<Book> findByAuthorAndTitle(String author, String title);
     List<Book> findAllRentableBooks();
}
