package com.linktera.linkteraquiz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private UUID bookId;
    private String title;
    private String author;
    //private Boolean isAvailable;
    private int count;

}
