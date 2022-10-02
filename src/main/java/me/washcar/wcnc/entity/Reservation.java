package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Getter
public class Reservation {
    @Id
    @GeneratedValue
    private long reservationNumber;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    private String extraOption;

    @ManyToOne
    private User user;

    @ManyToOne
    private Slot slot;

    @ManyToOne
    private SlotOption slotOption;
}
