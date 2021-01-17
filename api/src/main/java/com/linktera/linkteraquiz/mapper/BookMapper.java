package com.linktera.linkteraquiz.mapper;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.request.BookRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book toUpdateBook(BookRequest bookRequest);
}
