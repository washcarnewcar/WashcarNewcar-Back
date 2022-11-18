package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Getter
public class StoreReservationException {
    @Id
    @GeneratedValue
    private Long reservationExceptionId;

    private ZonedDateTime exceptionStartDateTime;

    private ZonedDateTime exceptionEndDateTime;

    @ManyToOne
    private Store store;

}
