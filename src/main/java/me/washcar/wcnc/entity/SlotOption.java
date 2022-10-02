package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class SlotOption {
    @Id
    @GeneratedValue
    private long slotOptionNumber;

    private Period TimeInterval;

    @OneToMany(mappedBy = "slotOption")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne
    private Slot slot;
}
