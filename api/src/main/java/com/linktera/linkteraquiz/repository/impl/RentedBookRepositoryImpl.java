package com.linktera.linkteraquiz.repository.impl;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.repository.RentedBookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Scope("singleton")
public class RentedBookRepositoryImpl implements RentedBookRepository  {

    Map<UUID, RentedBook> rentedBooks = new HashMap<>();

    @Override
    public List<RentedBook> getList() {
        return new ArrayList<>(rentedBooks.values());
    }

    @Override
    public RentedBook get(UUID uuid) {
        return rentedBooks.get(uuid);

    }

    @Override
    public void save(RentedBook entity) {
        UUID uuid = UUID.randomUUID();
        entity.setRentId(uuid);
        rentedBooks.put(uuid, entity);

    }

    @Override
    public void update(UUID uuid, RentedBook entity) {

        rentedBooks.replace(uuid, entity);
    }

    @Override
    public void delete(UUID uuid) {
        rentedBooks.remove(uuid);

    }

    @Override
    public List<RentedBook> findByUserId(UUID userId) {
        return rentedBooks.values().stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<RentedBook> findByUserIdAndEndDateNull(UUID userId) {
        return rentedBooks.values().stream().filter(t -> t.getUserId().equals(userId) && t.getEndDate() == null).collect(Collectors.toList());
    }

    @Override
    public List<RentedBook> findByBookId(UUID bookId) {
        return rentedBooks.values().stream().filter(t -> t.getBookId().equals(bookId)).collect(Collectors.toList());

    }

    @Override
    public List<RentedBook> findByBookIdAndEndDateNull(UUID bookId) {
        return rentedBooks.values().stream().filter(t -> t.getBookId().equals(bookId) && t.getEndDate() == null).collect(Collectors.toList());
    }

    @Override
    public List<RentedBook> findByEndDateNull() {
        return rentedBooks.values().stream().filter(t -> t.getEndDate() == null).collect(Collectors.toList());

    }


}
