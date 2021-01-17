package com.linktera.linkteraquiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentedBookDTO {

    private UUID rentId;
    private UUID bookId;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String title;
    private String author;
    private Date startDate;
    private Date endDate;

}
