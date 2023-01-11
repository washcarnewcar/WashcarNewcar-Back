package me.washcar.wcnc.repository;

import java.util.List;
import java.util.Optional;
import me.washcar.wcnc.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  Optional<List<Reservation>> findAllByStore_Id(Long storeId);

  Optional<List<Reservation>> findAllByStore_IdAndReservationStatus_Id(Long storeId, Long statusId);

}
