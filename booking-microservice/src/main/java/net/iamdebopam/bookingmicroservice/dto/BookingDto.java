package net.iamdebopam.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

	@NotNull
	private Long flightId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;
}
