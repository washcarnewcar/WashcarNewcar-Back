package me.washcar.wcnc.repository;

import java.util.Optional;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreOperateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOperateTimeRepository extends JpaRepository<StoreOperateTime, Long> {

  Optional<StoreOperateTime> findByStore(Store store);
}
