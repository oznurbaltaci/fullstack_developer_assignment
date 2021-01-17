package com.linktera.linkteraquiz.service.impl;

import com.linktera.linkteraquiz.dto.RentedBookDTO;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.mapper.RentedBookMapper;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.repository.BookRepository;
import com.linktera.linkteraquiz.repository.RentedBookRepository;
import com.linktera.linkteraquiz.repository.UserRepository;
import com.linktera.linkteraquiz.security.JwtClaimsModel;
import com.linktera.linkteraquiz.service.RentedBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentedBookServiceImpl implements RentedBookService {
    private final JwtClaimsModel jwtClaimsModel;
    private final RentedBookRepository rentedBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentedBookMapper rentedBookMapper;

    public List<RentedBookDTO> convertToDTOList(List<RentedBook> rentedBooks) {
        List<RentedBookDTO> rentedBookDTOs = new ArrayList<>();

        rentedBooks.forEach(x -> {
            rentedBookDTOs.add(convertToDTO(x));
        });

        return rentedBookDTOs;
    }

    public RentedBookDTO convertToDTO(RentedBook rentedBook) {
        return rentedBookMapper.toRentedBookDTO(
                bookRepository.findByBookId(rentedBook.getBookId()).get(),
                userRepository.findByUserId(rentedBook.getUserId()).get(),
                rentedBook);
    }

    @Override
    public List<RentedBookDTO> getAllRentedBooksHistoryByUserId(UUID userId) {
        return convertToDTOList(rentedBookRepository.findByUserId(userId));
    }

    @Override
    public List<RentedBookDTO> getAllRentedBooksByUserId(UUID userId) throws LinkteraServiceException {
        return convertToDTOList(rentedBookRepository.findByUserIdAndEndDateNull(userId));
    }

    @Override
    public List<RentedBookDTO> getAllRentedBooksHistoryByBookId(UUID bookId) {
        return convertToDTOList(rentedBookRepository.findByBookId(bookId));
    }

    @Override
    public List<RentedBookDTO> getAllRentedBooksByBookId(UUID bookId) {
        return convertToDTOList(rentedBookRepository.findByBookIdAndEndDateNull(bookId));
    }

    @Override
    public List<RentedBookDTO> getCurrentRentedBooks() {
        return convertToDTOList(rentedBookRepository.findByEndDateNull());
    }

    @Override
    public List<RentedBookDTO> getList() {
        return convertToDTOList(rentedBookRepository.getList());
    }

    @Override
    public RentedBookDTO get(UUID uuid) {
        return convertToDTO(rentedBookRepository.get(uuid));
    }

    @Override
    public void rentABook(UUID uuid) {
        Book book = bookRepository.get(uuid);
        if (book.getCount() == 0)
            throw new LinkteraServiceException(ErrorCode.CANNOT_BE_RENTED);

        RentedBook rentedBook = new RentedBook();
        rentedBook.setRentId(UUID.randomUUID());
        rentedBook.setUserId(jwtClaimsModel.getUserId());
        rentedBook.setBookId(uuid);
        rentedBook.setStartDate(new Date());
        rentedBookRepository.save(rentedBook);
        book.setCount(book.getCount() - 1);
    }


    @Override
    public void returnABook(UUID uuid) {
        RentedBook rentedBook = rentedBookRepository.get(uuid);
        Book book = bookRepository.get(rentedBook.getBookId());
        book.setCount(book.getCount() + 1);
        rentedBook.setEndDate(new Date());
    }
}
