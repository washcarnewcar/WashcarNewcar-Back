package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Getter
public class ReservationException {
    @Id
    @GeneratedValue
    private long reservationExceptionNumber;

    private ZonedDateTime exceptionStartDateTime;

    private ZonedDateTime exceptionEndDateTime;

    @ManyToOne
    private Slot slot;
}
