package com.linktera.linkteraquiz.service.impl;

import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.repository.BookRepository;
import com.linktera.linkteraquiz.repository.RentedBookRepository;
import com.linktera.linkteraquiz.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final RentedBookRepository rentedBookRepository;

    @Override
    public List<Book> getList() {
        return bookRepository.getList();
    }

    @Override
    public Book get(UUID uuid) {
//        Book book = bookRepository.get(uuid);
//        if(book == null)
        return bookRepository.get(uuid);
    }

    @Override
    public void save(Book dto) {
        if (bookRepository.findByAuthorAndTitle(dto.getAuthor(), dto.getTitle()).isPresent()) {
            throw new LinkteraServiceException(ErrorCode.DUPLICATE_ENTRY);
        }

        dto.setBookId(UUID.randomUUID());

        bookRepository.save(dto);
    }

    @Override
    public void update(UUID uuid, Book dto) {
        /*Book book = Book.builder().bookId(uuid)
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .count(dto.getCount()).build();*/
        // Kiralanan kitap var ise degistirilemez
        if (rentedBookRepository.findByBookIdAndEndDateNull(uuid).size() > 0) {
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN_OPERATION);
        } else
            bookRepository.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        // Kiralanan kitap var ise degistirilemez
        if (rentedBookRepository.findByBookIdAndEndDateNull(uuid).size() > 0) {
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN_OPERATION);
        } else
            bookRepository.delete(uuid);
    }

    @Override
    public List<Book> getAllRentableBooks() {
        return bookRepository.findAllRentableBooks();
    }
}
