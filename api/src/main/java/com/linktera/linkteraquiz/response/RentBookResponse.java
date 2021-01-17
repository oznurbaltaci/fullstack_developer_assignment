package com.linktera.linkteraquiz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RentBookResponse {

    @JsonProperty("books")
    @JsonInclude(Include.NON_NULL)
    private List<RentedBook> books;

    public RentBookResponse(List<RentedBook> books) {
        this.books = books;
    }

}
