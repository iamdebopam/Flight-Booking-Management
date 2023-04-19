package net.iamdebopam.bookingmicroservice.feignclient;


import net.iamdebopam.bookingmicroservice.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "flight",url = "http://localhost:8080/flights")
@FeignClient(name = "FLIGHT")
public interface FlightClient {

	@PostMapping("/reserve/{id}")
	FlightDto reserveSeat(@PathVariable Long id);
}
