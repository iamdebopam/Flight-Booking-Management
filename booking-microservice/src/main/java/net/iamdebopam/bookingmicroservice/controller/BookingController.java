package net.iamdebopam.bookingmicroservice.controller;

import net.iamdebopam.bookingmicroservice.dto.BookingDto;
import net.iamdebopam.bookingmicroservice.model.Booking;
import net.iamdebopam.bookingmicroservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping("/")
    public ResponseEntity<Iterable<Booking>> getAllBooking(@RequestHeader Long userId){
        Iterable<Booking>bookingList= bookingService.getAllBookings(userId);
        return new ResponseEntity<>(bookingList, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingByBookingIdAndUserId(@PathVariable("id")Long bookingId,
                                                                  @RequestHeader Long userId){
        Booking booking=bookingService.getBookingByBookingIdAndUserId(bookingId, userId);
        return new ResponseEntity<>(booking,HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<Booking> createBooking (@RequestHeader Long userId,
                                                  @RequestBody BookingDto bookingDto){
        Booking booking=bookingService.createNewBooking(userId,bookingDto);
        return new ResponseEntity<>(booking,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@RequestHeader Long userId, @PathVariable("id") Long bookingId) {
        bookingService.deleteBookingByBookingIdAndUserId(bookingId, userId);
        return "BookingId is deleted Successfully !!";
    }
}
