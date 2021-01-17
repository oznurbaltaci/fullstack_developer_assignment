package com.linktera.linkteraquiz.web.controller;

import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.response.BookResponse;
import com.linktera.linkteraquiz.security.JwtClaimsModel;
import com.linktera.linkteraquiz.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/book")
public class BookController {

    private final JwtClaimsModel jwtClaimsModel;
    private final BookService bookService;

    @GetMapping()
    public BookResponse getBooks() { return new BookResponse(bookService.getList());
    }

    @GetMapping("/{uuid}")
    public BookResponse getBook(@PathVariable("uuid") UUID uuid) {
        if (uuid == null || bookService.get(uuid) == null) {
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }

        return new BookResponse(bookService.get(uuid));
    }

    @PostMapping()
    public void saveBook(@RequestBody BookRequest request) {
        if (request == null || request.getBook() == null) {
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        bookService.save(request.getBook());
    }

    @PutMapping("/{uuid}")
    public void updateBook(@RequestBody BookRequest request, @PathVariable("uuid") UUID uuid) {
        if (request == null || request.getBook() == null || uuid == null) {
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        bookService.update(uuid, request.getBook());
    }

    @DeleteMapping("/{uuid}")
    public void deleteBook(@PathVariable("uuid") UUID uuid) {
        if (uuid == null) {
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        bookService.delete(uuid);
    }

}
