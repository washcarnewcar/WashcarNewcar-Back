package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StoreImageRepo extends JpaRepository<StoreImage, Long> {
    Collection<StoreImage> findByStore(Store store);

}
