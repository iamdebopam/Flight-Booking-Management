package net.iamdebopam.checkinmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "checkIn")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long bookingId;

    @NotNull
    private Long flightId;

    @Max(value = 150, message = "a flight cannot have more than 150 seats")
    @Min(value = 1, message = "a flight cannot have negative remaining seats")
    private Integer seatNumber;

    @NotNull
    private Boolean checkInStatus;
}
