package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
