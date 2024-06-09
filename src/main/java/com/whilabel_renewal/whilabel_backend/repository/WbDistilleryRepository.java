package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.WbDistillery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WbDistilleryRepository extends JpaRepository<WbDistillery,Long> {
}
