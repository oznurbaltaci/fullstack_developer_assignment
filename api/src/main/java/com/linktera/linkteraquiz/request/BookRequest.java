package com.linktera.linkteraquiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Book;
import lombok.Data;
import lombok.Getter;

@Data
public class BookRequest {

    @JsonProperty("book")
    private Book book;

}
