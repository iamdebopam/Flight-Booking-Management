package net.iamdebopam.bookingmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column
	private Long userId;

	@NotNull
	private Long flightId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	@Size(min = 4, max = 5, message = "flight number must be alphanumeric string of length 4")
	private String flightNumber;

	@NotNull
	@Size(min = 3, max = 3, message = "source must be string of length 3")
	private String source;

	@NotNull
	@Size(min = 3, max = 3, message = "destination must be string of length 3")
	private String destination;

	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@Min(value = 1000)
	@Max(value = 100000)
	private Integer fare;

	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 1, message = "a flight cannot have negative remaining seats")
	private Integer seatNumber;

	@NotNull
	private Boolean checkinStatus;

}
