package com.linktera.linkteraquiz.mapper;

import com.linktera.linkteraquiz.dto.RentedBookDTO;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.BookRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RentedBookMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(source = "rentedBook.userId", target = "userId")
    @Mapping(source = "rentedBook.bookId", target = "bookId")

    RentedBookDTO toRentedBookDTO(Book book, User user, RentedBook rentedBook);
}
