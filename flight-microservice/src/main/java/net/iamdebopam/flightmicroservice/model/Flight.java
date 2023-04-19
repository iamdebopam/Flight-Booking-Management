package net.iamdebopam.flightmicroservice.model;
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
@Entity
@Table(name="Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 5, message = "Flight number must have Alphanumeric String of length 5")
    private String number;

    @Column(nullable = false)
    @Size(min = 3, max = 3, message = "Source must be string of length 3")
    private String source;

    @Column(nullable = false)
    @Size(min = 3, max = 3, message = "destination must be string of length 3")
    private String destination;

    @Column(nullable = false)
    @FutureOrPresent
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    @Min(value = 5000)
    @Max(value = 10000)
    private Integer price;

    @Column(nullable = false)
    @Max(value = 150, message = "a Flight cannot have more than 150 seats")
    @Min(value = 0, message = "a Flight cannot have negative remaining seats")
    private Integer totalSeats;

    @Column(nullable = false)
    @Max(value = 150, message = "a Flight cannot have more than 150 seats")
    @Min(value = 0, message = "a Flight cannot have negative remaining seats")
    private Integer remainingSeats;

}
