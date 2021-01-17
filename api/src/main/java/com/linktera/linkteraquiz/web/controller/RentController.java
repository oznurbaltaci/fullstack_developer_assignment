package com.linktera.linkteraquiz.web.controller;

import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.request.RentBookRequest;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import com.linktera.linkteraquiz.response.BookResponse;
import com.linktera.linkteraquiz.response.RentBookDTOResponse;
import com.linktera.linkteraquiz.response.RentBookResponse;
import com.linktera.linkteraquiz.security.JwtClaimsModel;
import com.linktera.linkteraquiz.service.BookService;
import com.linktera.linkteraquiz.service.RentedBookService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/rent")
public class RentController {
    private final JwtClaimsModel jwtClaimsModel;
    private final RentedBookService rentedBookService;


    @PostMapping("/{uuid}")
    @ApiOperation("Kullanıcı kitap kiralama islemini gerçekleştirir.")
    public ResponseEntity<Void> rentABook(@PathVariable("uuid") UUID bookId) {
        if (bookId == null)
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
//        if (jwtClaimsModel.getUserId() != rentBookRequest.getRentBook().getUserId())
//            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        rentedBookService.rentABook(bookId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation("Kullanıcı kitabi iade eder.")
    public ResponseEntity<Void> returnABook(@PathVariable("uuid") UUID rentId) {
        if (rentId == null)
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);

//        if (jwtClaimsModel.getUserId() != rentBookRequest.getRentBook().getUserId())
//            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        rentedBookService.returnABook(rentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history")
    @ApiOperation("Kiralanan kitap gecmisini getirilir.")
    public ResponseEntity<RentBookDTOResponse> getAllRentedBooks() {
        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getList()), HttpStatus.OK);
    }

    @GetMapping("/current")
    @ApiOperation("Guncel kiralanmis kitaplar getirilir.")
    public ResponseEntity<RentBookDTOResponse> getCurrentRentedBooks() {
        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getCurrentRentedBooks()), HttpStatus.OK);
    }

    @GetMapping(value = {"/historyUser", "/historyUser/{userId}"})
    @ApiOperation("Kullanicinin kiralama gecmisini getirilir.")
    public ResponseEntity<RentBookDTOResponse> getAllRentedBooksHistoryByUserId(@PathVariable(required = false) UUID userId) {
        if (userId == null)
            userId = jwtClaimsModel.getUserId();

        if(jwtClaimsModel.getUserId() != userId && jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getAllRentedBooksHistoryByUserId(userId)), HttpStatus.OK);
    }

    @GetMapping(value = {"/rentedUser", "/rentedUser/{userId}"})
    @ApiOperation("Kullanicinin guncel kiraladigi kitaplar getirilir.")
    public ResponseEntity<RentBookDTOResponse> getAllRentedBooksByUserId(@PathVariable(required = false) UUID userId) {
        if (userId == null)
            userId = jwtClaimsModel.getUserId();

        if(jwtClaimsModel.getUserId() != userId && jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getAllRentedBooksByUserId(userId)), HttpStatus.OK);
    }

    @GetMapping("/historyBook/{uuid}")
    @ApiOperation("Kitabin kiralama gecmisini getirilir.")
    public ResponseEntity<RentBookDTOResponse> getAllRentedBooksHistoryByBookId(@PathVariable("uuid") UUID bookId) {
        if (bookId == null)
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getAllRentedBooksHistoryByBookId(bookId)), HttpStatus.OK);
    }

    @GetMapping("/rentedBook/{uuid}")
    @ApiOperation("Kitabin guncel kiralanmalarini getirilir.")
    public ResponseEntity<RentBookDTOResponse> getAllRentedBooksByBookId(@PathVariable("uuid") UUID bookId) {
        if (bookId == null)
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        return new ResponseEntity<>(new RentBookDTOResponse(rentedBookService.getAllRentedBooksByBookId(bookId)), HttpStatus.OK);
    }

}
