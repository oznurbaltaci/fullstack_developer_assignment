package com.linktera.linkteraquiz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentedBook {

    private UUID rentId;
    private UUID bookId;
    private UUID userId;
    private Date startDate;
    private Date endDate;

}
