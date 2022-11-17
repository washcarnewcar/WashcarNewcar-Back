package me.washcar.wcnc.repository;

import java.util.Optional;
import me.washcar.wcnc.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

  // Optional 로 감싸서 값을 가져오도록 수정.
  Optional<Store> findBySlug(String slug);
}
