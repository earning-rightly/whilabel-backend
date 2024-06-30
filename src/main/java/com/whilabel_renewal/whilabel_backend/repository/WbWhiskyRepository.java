package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.WbWhisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WbWhiskyRepository extends JpaRepository<WbWhisky,Long> {

    @Query(value = "SELECT id FROM WB_WHISKY WHERE barcode = ?", nativeQuery = true)
    String findByBarcode(String barcode);

}
