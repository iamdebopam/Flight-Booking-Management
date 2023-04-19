package net.iamdebopam.bookingmicroservice.service;

import net.iamdebopam.bookingmicroservice.dto.BookingDto;
import net.iamdebopam.bookingmicroservice.dto.CheckinDto;
import net.iamdebopam.bookingmicroservice.model.Booking;

public interface BookingService {

    Booking getBookingByBookingIdAndUserId(Long bookingId, Long userId);

    Iterable<Booking> getAllBookings(Long userId);

    Booking createNewBooking(Long userId, BookingDto bookingDto);

    Booking updateCheckinStatus(CheckinDto checkinDto);

    void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId);
}
