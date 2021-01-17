package com.linktera.linkteraquiz.repository.impl;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.repository.BookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Scope("singleton")
public class BookRepositoryImpl implements BookRepository {
    //List<Book> books = new ArrayList<>();
    Map<UUID, Book> books;

    public BookRepositoryImpl() {
        this.books = new HashMap<UUID, Book>();
        for (int i = 0; i < 5; i++) {
            UUID uuid = UUID.randomUUID();
            books.put(uuid, new Book(uuid, "kitap" + i, "oznur" + i, i));
        }
    }

    @Override
    public List<Book> getList() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book get(UUID uuid) {
        return books.get(uuid);
    }

    @Override
    public void save(Book entity) {
        UUID uuid = UUID.randomUUID();

        entity.setBookId(uuid);

        books.put(uuid, entity);
    }

    @Override
    public void update(UUID uuid, Book entity) {
        books.replace(uuid, entity);
    }

    @Override
    public void delete(UUID uuid) {
        books.remove(uuid);
    }

    @Override
    public Optional<Book> findByBookId(UUID bookId) {
        return books.values().stream().filter(t -> t.getBookId().equals(bookId)).findFirst();
    }

    @Override
    public Optional<Book> findByAuthorAndTitle(String author, String title) {
        return books.values().stream().filter(book -> book.getAuthor().equals(author) && book.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Book> findAllRentableBooks() {
        return books.values().stream().filter(book -> book.getCount() > 0).collect(Collectors.toList());
    }
}
