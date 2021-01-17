package com.linktera.linkteraquiz.service;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService extends BaseService<Book> {

     List<Book> getAllRentableBooks();
     public void save(Book dto);

}
