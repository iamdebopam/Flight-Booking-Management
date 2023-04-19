package net.iamdebopam.flightmicroservice.service;

import lombok.Data;
import net.iamdebopam.flightmicroservice.model.Flight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    List<Flight> getFlightUsingSourceAndDestinationAndDate(String source, String destination, Date date);

    Flight reserveSeat(Long id);
    void vacantSeat(Long id);
}
