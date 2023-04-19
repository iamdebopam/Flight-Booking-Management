package net.iamdebopam.checkinmicroservice.controller;

import net.iamdebopam.checkinmicroservice.dto.CheckinDto;
import net.iamdebopam.checkinmicroservice.model.CheckIn;
import net.iamdebopam.checkinmicroservice.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;

    @PostMapping("/")
    public ResponseEntity<CheckIn> create(@RequestHeader Long userId, @RequestBody CheckinDto checkinDto){
        CheckIn checkIn=checkInService.createNewCheckIn(checkinDto.getBookingId(),userId);
        return new ResponseEntity<>(checkIn, HttpStatus.CREATED);
    }
}
