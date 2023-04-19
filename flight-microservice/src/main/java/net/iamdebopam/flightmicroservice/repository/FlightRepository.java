package net.iamdebopam.flightmicroservice.repository;

import net.iamdebopam.flightmicroservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findBySourceAndDestinationAndDate(String source, String destination, Date date);
}
