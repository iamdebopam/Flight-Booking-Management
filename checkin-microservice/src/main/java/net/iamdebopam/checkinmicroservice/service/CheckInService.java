package net.iamdebopam.checkinmicroservice.service;

import net.iamdebopam.checkinmicroservice.model.CheckIn;
import net.iamdebopam.checkinmicroservice.model.PendingCheckIn;

public interface CheckInService {

    CheckIn createNewCheckIn(Long bookingId,Long userId);
    void createNewPendingCheckIn(PendingCheckIn pendingCheckIn);
    void deleteExistingCheckInOrPendingCheckIn(Long bookingId);
}
