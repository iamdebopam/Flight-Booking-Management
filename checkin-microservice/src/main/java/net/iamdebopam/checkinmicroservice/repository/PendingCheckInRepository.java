package net.iamdebopam.checkinmicroservice.repository;

import net.iamdebopam.checkinmicroservice.model.PendingCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendingCheckInRepository extends JpaRepository<PendingCheckIn,Long> {
    Optional<PendingCheckIn> findByBookingIdAndUserId(Long bookingId, Long userId);

    void deleteByBookingId(Long bookingId);

}
