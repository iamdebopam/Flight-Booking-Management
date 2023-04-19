package net.iamdebopam.checkinmicroservice.service;

import net.iamdebopam.checkinmicroservice.model.CheckIn;
import net.iamdebopam.checkinmicroservice.model.PendingCheckIn;
import net.iamdebopam.checkinmicroservice.repository.CheckInRepository;
import net.iamdebopam.checkinmicroservice.repository.PendingCheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CheckInServiceImpl implements CheckInService{
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private CheckInRepository checkInRepository;
    @Autowired
    private PendingCheckInRepository pendingCheckInRepository;
    @Override
    @Transactional
    public CheckIn createNewCheckIn(Long bookingId, Long userId) {
        PendingCheckIn pendingCheckIn=pendingCheckInRepository.findByBookingIdAndUserId(bookingId,userId)
                .orElseThrow(()->new RuntimeException("booking not found or already checkedIn"));

        int seatNumber=generateSeatNumber(pendingCheckIn);
        CheckIn checkIn=new CheckIn();
        checkIn.setSeatNumber(seatNumber);
        checkIn.setBookingId(pendingCheckIn.getBookingId());
        checkIn.setFlightId(pendingCheckIn.getFlightId());
        checkIn.setCheckInStatus(Boolean.TRUE);

        CheckIn saved=checkInRepository.save(checkIn);
        pendingCheckInRepository.deleteByBookingId(bookingId);

        streamBridge.send("checkInUpdate", Map.of(
                "bookingId",bookingId,
                "userId", userId,
                "seatNumber", seatNumber
        ));


        return saved;
    }

    @Override
    public void createNewPendingCheckIn(PendingCheckIn pendingCheckIn) {
        pendingCheckInRepository.save(pendingCheckIn);
    }

    @Override
    @Transactional
    public void deleteExistingCheckInOrPendingCheckIn(Long bookingId) {
        pendingCheckInRepository.deleteByBookingId(bookingId);
        checkInRepository.deleteByBookingId(bookingId);
    }

    public int generateSeatNumber(PendingCheckIn pendingCheckIn){
        List<CheckIn>checkInList=checkInRepository.findByFlightId(pendingCheckIn.getFlightId());
        Set<Integer>allocatedSeatNumber= new HashSet<>();

        checkInList.forEach(checkIn -> allocatedSeatNumber.add(checkIn.getSeatNumber()));
        for(int seatNumber = 1; seatNumber < pendingCheckIn.getTotalSeats(); seatNumber++){
            if(!allocatedSeatNumber.contains(seatNumber))
                return seatNumber;
        }
        return 0;
    }
}
