package me.washcar.wcnc.repository;

import java.util.List;
import me.washcar.wcnc.dto.ReservationException;
import me.washcar.wcnc.entity.Model;
import me.washcar.wcnc.entity.StoreReservationException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationExceptionRepository extends
    JpaRepository<StoreReservationException, Long> {

  List<StoreReservationException> findAllByStore_StoreId(Long storeId);
}
