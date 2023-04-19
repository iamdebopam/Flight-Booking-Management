package net.iamdebopam.checkinmicroservice.config;

import net.iamdebopam.checkinmicroservice.dto.DeleteDto;
import net.iamdebopam.checkinmicroservice.model.PendingCheckIn;
import net.iamdebopam.checkinmicroservice.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CheckinConfig {

    @Autowired
    private CheckInService checkInService;

    @Bean
    Consumer<DeleteDto> onBookingDeletionReceive() {
        return deleteDto -> {
            checkInService.deleteExistingCheckInOrPendingCheckIn(deleteDto.getBookingId());
        };
    }
    @Bean
    Consumer<PendingCheckIn> onPendingCheckInReceive(){
        return pendingCheckIn -> {
            checkInService.createNewPendingCheckIn(pendingCheckIn);
        };
    }


}
