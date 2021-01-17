package com.linktera.linkteraquiz.service;

import com.linktera.linkteraquiz.dto.RentedBookDTO;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.service.base.BaseService;

import java.util.List;
import java.util.UUID;

public interface RentedBookService {

     List<RentedBookDTO> getList();
     RentedBookDTO get(UUID uuid);
     void rentABook(UUID uuid);
     void returnABook(UUID uuid);
     List<RentedBookDTO> getAllRentedBooksHistoryByUserId(UUID userId);
     List<RentedBookDTO> getAllRentedBooksByUserId(UUID userId);
     List<RentedBookDTO> getAllRentedBooksHistoryByBookId(UUID bookId);
     List<RentedBookDTO> getAllRentedBooksByBookId(UUID bookId);
     List<RentedBookDTO> getCurrentRentedBooks();

}
