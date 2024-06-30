package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query(value = "SELECT id,title,created_date_time FROM ANNOUNCEMENT ORDER BY created_date_time DESC LIMIT 10 OFFSET ? * 10", nativeQuery = true)
    List<Announcement> getAnnouncementList(int page);

}
