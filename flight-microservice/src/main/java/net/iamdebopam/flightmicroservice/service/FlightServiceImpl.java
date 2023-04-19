package net.iamdebopam.flightmicroservice.service;

import net.iamdebopam.flightmicroservice.model.Flight;
import net.iamdebopam.flightmicroservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightRepository flightRepository;
    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        Optional<Flight>optionalFlight=flightRepository.findById(id);
        if (optionalFlight.isEmpty()) {
            throw new RuntimeException();
        }
        return optionalFlight.get();
    }

    @Override
    public List<Flight> getFlightUsingSourceAndDestinationAndDate(String source, String destination, Date date) {
        return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
    }

    @Override
    public Flight reserveSeat(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isEmpty()) {
            throw new RuntimeException();
        }
        Flight flight = optionalFlight.get();
        flight.setRemainingSeats(flight.getRemainingSeats() - 1);
        return flightRepository.save(flight);
    }

    @Override
    public void vacantSeat(Long id) {
        Optional<Flight>optionalFlight=flightRepository.findById(id);
        if(optionalFlight.isEmpty()){
            throw new RuntimeException();
        }
        Flight flight=optionalFlight.get();
        flight.setRemainingSeats(flight.getRemainingSeats()+1);
        flightRepository.save(flight);
    }
}
