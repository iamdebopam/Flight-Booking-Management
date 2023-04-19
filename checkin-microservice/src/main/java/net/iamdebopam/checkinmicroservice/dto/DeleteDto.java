package net.iamdebopam.checkinmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDto {

	@NotNull
	private Long flightId;

	@NotNull
	private Long bookingId;

}
