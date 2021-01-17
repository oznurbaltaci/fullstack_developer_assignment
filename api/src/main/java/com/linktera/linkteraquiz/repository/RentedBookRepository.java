package com.linktera.linkteraquiz.repository;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface RentedBookRepository extends BaseRepository<RentedBook>{
    List<RentedBook> findByUserId(UUID userId);
    List<RentedBook> findByUserIdAndEndDateNull(UUID userId);
    List<RentedBook> findByBookId(UUID bookId);
    List<RentedBook> findByBookIdAndEndDateNull(UUID bookId);
    List<RentedBook> findByEndDateNull();

}
