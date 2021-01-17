package com.linktera.linkteraquiz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linktera.linkteraquiz.dto.RentedBookDTO;
import com.linktera.linkteraquiz.model.RentedBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RentBookDTOResponse {

    @JsonProperty("books")
    @JsonInclude(Include.NON_NULL)
    private List<RentedBookDTO> books;

    public RentBookDTOResponse(List<RentedBookDTO> books) {
        this.books = books;
    }

}
