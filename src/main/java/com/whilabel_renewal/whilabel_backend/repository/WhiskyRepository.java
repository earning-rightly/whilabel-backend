package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky,Long> {

    @Query(value = "SELECT id FROM WHISKY WHERE barcode = ?", nativeQuery = true)
    String findByBarcode(String barcode);

}
