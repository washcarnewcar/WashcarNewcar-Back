package me.washcar.wcnc.repository;

import java.util.List;
import java.util.Optional;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    Optional<Collection<StoreImage>> findByStore(Store store);

    Optional<List<StoreImage>> findByStore_Id(Long storeId);
}
