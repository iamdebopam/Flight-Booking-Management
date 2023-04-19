package net.iamdebopam.flightmicroservice.controller;

import lombok.AllArgsConstructor;
import net.iamdebopam.flightmicroservice.model.Flight;
import net.iamdebopam.flightmicroservice.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    //get all flight details
    @GetMapping("/")
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight>flightList=flightService.getAllFlights();
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    //get flight details by id
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        Flight flight=flightService.getFlightById(id);
        return new ResponseEntity<>(flight,HttpStatus.OK);
    }

    //get flight details using Source , Destination and Date
    @GetMapping("/{from}/{to}/{date}")
    public ResponseEntity<List<Flight>> getFlightBySourceAndDestinationAndDate(@PathVariable("from")String source,
                                                                         @PathVariable("to")String destination,
                                                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<Flight> flightList=flightService.getFlightUsingSourceAndDestinationAndDate(source, destination, date);
        return new ResponseEntity<>(flightList,HttpStatus.OK);
    }

    //reserve seat which is called from Booking using feignClient
    @PostMapping("/reserve/{id}")
    public ResponseEntity<Flight> reserveSeat(@PathVariable Long id){
        Flight flight=flightService.reserveSeat(id);
        return new ResponseEntity<>(flight,HttpStatus.CREATED);
    }
}
