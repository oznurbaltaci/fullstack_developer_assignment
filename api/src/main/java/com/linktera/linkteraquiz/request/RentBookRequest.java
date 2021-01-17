package com.linktera.linkteraquiz.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.RentedBook;
import lombok.Getter;

@Getter
public class RentBookRequest {
    @JsonProperty("rentBook")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RentedBook rentBook;
}
