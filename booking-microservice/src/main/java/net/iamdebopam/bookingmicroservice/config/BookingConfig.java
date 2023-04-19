package net.iamdebopam.bookingmicroservice.config;

import net.iamdebopam.bookingmicroservice.dto.CheckinDto;
import net.iamdebopam.bookingmicroservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class BookingConfig {
    @Autowired
    private BookingService bookingService;

    @Bean
    public Consumer<CheckinDto> onCheckinDtoReceive() {
        return checkinDto -> {
            bookingService.updateCheckinStatus(checkinDto);
        };
    }
}
