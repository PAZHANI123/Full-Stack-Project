package com.example.partypro.partypro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.partypro.partypro.constant.Api;
import com.example.partypro.partypro.dto.request.BookingRequest;
import com.example.partypro.partypro.dto.response.BookingResponse;
import com.example.partypro.partypro.model.Booking;
import com.example.partypro.partypro.service.Impl.BookingServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Api.USER)
@RequiredArgsConstructor
@Tag(name = "Booking")

public class BookingCon {
    private final BookingServiceImpl bookingServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<String> saveProduct(@RequestBody Booking request) {
        boolean isSaved = bookingServiceImpl.saveBooking(request);
        return isSaved ? ResponseEntity.status(201).body("Student added successfully!")
                : ResponseEntity.badRequest().build();
    }
     @GetMapping("/get")
    public ResponseEntity<List<Booking>> getAllProducts() {
        List<Booking> studentList = bookingServiceImpl.getAllBooking();
        return !studentList.isEmpty() ? ResponseEntity.status(200).body(studentList)
                : ResponseEntity.noContent().build();
    }

    

    @GetMapping("/find/{sid}")
    public ResponseEntity<BookingResponse> getProduct(@PathVariable Long sid) {
        BookingResponse bookingResponse = bookingServiceImpl.getBooking(sid);
        return bookingResponse != null ? ResponseEntity.ok().body(bookingResponse) : ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{sid}")
    public ResponseEntity<BookingResponse> updateProduct(@RequestBody BookingRequest request, @PathVariable Long sid) {
        BookingResponse bookingResponse = bookingServiceImpl.updateBooking(request, sid);
        return bookingResponse != null ? ResponseEntity.ok().body(bookingResponse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{sid}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long sid) {
        boolean isDeleted = bookingServiceImpl.deleteBooking(sid);
        return isDeleted ? ResponseEntity.ok().body("Student deleted successfully!")
                : ResponseEntity.notFound().build();
    }
}
