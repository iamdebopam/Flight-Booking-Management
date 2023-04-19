package net.iamdebopam.bookingmicroservice.service;

import net.iamdebopam.bookingmicroservice.dto.BookingDto;
import net.iamdebopam.bookingmicroservice.dto.CheckinDto;
import net.iamdebopam.bookingmicroservice.dto.FlightDto;
import net.iamdebopam.bookingmicroservice.feignclient.FlightClient;
import net.iamdebopam.bookingmicroservice.model.Booking;
import net.iamdebopam.bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private FlightClient flightClient;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private StreamBridge streamBridge;
    @Override
    public Booking getBookingByBookingIdAndUserId(Long bookingId, Long userId) {
        return bookingRepository.findByIdAndUserId(bookingId,userId)
                .orElseThrow(()-> new RuntimeException("booking not found"));

    }

    @Override
    public Iterable<Booking> getAllBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking createNewBooking(Long userId, BookingDto bookingDto) {
        if(bookingExists(userId,bookingDto.getFlightId())){
            throw new RuntimeException("booking already exists");
        }
        FlightDto flightDto=flightClient.reserveSeat(bookingDto.getFlightId());
        Booking booking=new Booking();
        booking.setUserId(userId);
        booking.setFirstName(bookingDto.getFirstName());
        booking.setLastName(bookingDto.getLastName());
        booking.setFlightId(flightDto.getId());
        booking.setFlightNumber(flightDto.getNumber());
        booking.setSource(flightDto.getSource());
        booking.setDestination(flightDto.getDestination());
        booking.setDate(flightDto.getDate());
        booking.setFare(flightDto.getPrice());
        booking.setCheckinStatus(Boolean.FALSE);

        Booking saveBooking= bookingRepository.save(booking);

        streamBridge.send("pending-checkin", Map.of(
                "bookingId", saveBooking.getId(),
                "flightId", saveBooking.getFlightId(),
                "userId", saveBooking.getUserId(),
                "totalSeats",flightDto.getTotalSeats()
        ));

        return saveBooking;
    }

    @Override
    public Booking updateCheckinStatus(CheckinDto checkinDto) {
        Booking booking = bookingRepository.findByIdAndUserId(checkinDto.getBookingId(), checkinDto.getUserId())
                .orElseThrow(() -> new RuntimeException("booking not found"));

        booking.setCheckinStatus(Boolean.TRUE);
        booking.setSeatNumber(checkinDto.getSeatNumber());

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId)
                .orElseThrow(() -> new RuntimeException("booking not found"));

        Long flightId = booking.getFlightId();
        bookingRepository.delete(booking);

        streamBridge.send("booking-deletion", Map.of(
                "flightId", flightId,
                "bookingId", bookingId
        ));
    }

    private boolean bookingExists(Long userId, Long flightId) {
        return bookingRepository.findByUserIdAndFlightId(userId, flightId).isPresent();
    }
}
