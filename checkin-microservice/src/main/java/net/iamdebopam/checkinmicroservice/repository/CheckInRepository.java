package net.iamdebopam.checkinmicroservice.repository;

import net.iamdebopam.checkinmicroservice.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn,Long> {
    List<CheckIn> findByFlightId(Long flightId);

    void deleteByBookingId(Long bookingId);
}
