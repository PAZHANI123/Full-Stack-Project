package com.example.partypro.partypro.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.Subject;

// import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.partypro.partypro.dto.request.BookingRequest;
import com.example.partypro.partypro.dto.response.BookingResponse;
import com.example.partypro.partypro.model.Booking;
import com.example.partypro.partypro.repository.BookingRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl {

    private final BookingRepo bookingRepo;

    public boolean saveBooking(Booking request) {
        if (bookingRepo.findBybName(request.getBName()).isPresent()) {
            return false;
        }

        var booking = Booking.builder()
                .bName(request.getBName())
                .contact(request.getContact())
                .dateOfParty(request.getDateOfParty())
                .location(request.getLocation())
                .time(request.getTime())
                .accomodation(request.getAccomodation())
                .cuisine(request.getCuisine())
                .theme(request.getTheme())
                // .phographer(request.())
                // .djconcert(request.get())
                .customizedService(request.getCustomizedService())
                .build();
        bookingRepo.save(booking);

        return true;
    }

    public List<Booking> getAllBooking() {
        List<Booking> bookingList =  bookingRepo.findAll();
       return bookingList;
        
    }

    public BookingResponse getBooking(Long sid) {
        Booking booking = bookingRepo.findById(sid).get();
        return mapBookingToResponse(booking);
    }

    public BookingResponse updateBooking(BookingRequest request, Long sid) {
        Booking Booking = bookingRepo.findById(sid).get();

        if (Booking != null) {
            Booking.setBName(request.getBName());
            Booking.setContact(request.getContact());
            Booking.setDateOfParty(request.getDateOfParty());
            Booking.setLocation(request.getLocation());
            Booking.setTime(request.getTime());
            Booking.setAccomodation(request.getAccomodation());
            Booking.setCuisine(request.getCuisine());
            Booking.setTheme(request.getTheme());
            bookingRepo.save(Booking);

            return mapBookingToResponse(Booking);
        } else {
            throw new EntityNotFoundException("Student with pid " + sid + " not found");
        }
    }

    public boolean deleteBooking(Long sid) {
        Booking booking = bookingRepo.findById(sid).get();

        if (booking != null) {
            bookingRepo.deleteById(sid);
            return true;
        } else {
            return false;
        }
    }

    private BookingResponse mapBookingToResponse(Booking request) {
        return BookingResponse.builder()
        .bName(request.getBName())
        .contact(request.getContact())
        .dateOfParty(request.getDateOfParty())
        .location(request.getLocation())
        .time(request.getTime())
        .accomodation(request.getAccomodation())
        .cuisine(request.getCuisine())
        .theme(request.getTheme())
        .customizedService(request.getCustomizedService())
                .build();
    }

}
