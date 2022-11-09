//package me.washcar.wcnc.entity;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//public class Slot {
//    @Id
//    @GeneratedValue
//    private Long slotId;
//
//    private LocalTime sunOpen;
//    private LocalTime sunClose;
//
//    private LocalTime monOpen;
//    private LocalTime monClose;
//
//    private LocalTime tueOpen;
//    private LocalTime tueClose;
//
//    private LocalTime wedOpen;
//    private LocalTime wedClose;
//
//    private LocalTime thuOpen;
//    private LocalTime thuClose;
//
//    private LocalTime friOpen;
//    private LocalTime friClose;
//
//    private LocalTime satOpen;
//    private LocalTime satClose;
//
//    @ManyToOne
//    private Store store;
//
//    @OneToMany(mappedBy = "slot")
//    private List<Reservation> reservations = new ArrayList<>();
//
//    @OneToMany(mappedBy = "slot")
//    private List<SlotOption> slotOptions = new ArrayList<>();
//
//    @OneToMany(mappedBy = "slot")
//    private List<ReservationException> reservationExceptions = new ArrayList<>();
//}
