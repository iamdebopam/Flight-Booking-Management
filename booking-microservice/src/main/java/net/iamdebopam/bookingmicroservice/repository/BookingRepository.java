package net.iamdebopam.bookingmicroservice.repository;

import net.iamdebopam.bookingmicroservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long userId);

    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);

    Optional<Booking>findByUserIdAndFlightId(Long userId,Long flightId);

}
