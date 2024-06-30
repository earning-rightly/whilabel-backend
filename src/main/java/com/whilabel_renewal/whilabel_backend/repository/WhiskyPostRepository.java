package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.WhiskyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiskyPostRepository extends JpaRepository<WhiskyPost,Long> {


    @Query(value = "SELECT * FROM WHISKY_POST WHERE user_id = ?1 ORDER BY create_date_time DESC LIMIT 10 OFFSET ?2 * 10",nativeQuery = true)
    List<WhiskyPost> getByRecent(Long userId,  int page);

    @Query(value = "SELECT * FROM WHISKY_POST WHERE user_id = ?1 ORDER BY create_date_time ASC LIMIT 10 OFFSET ?2 * 10",nativeQuery = true)
    List<WhiskyPost> getByOldest(Long userId,  int page);

    @Query(value = "SELECT * FROM WHISKY_POST WHERE user_id = ?1 ORDER BY rating DESC LIMIT 10 OFFSET ?2 * 10",nativeQuery = true)
    List<WhiskyPost> getByRatingAscend(Long userId,  int page);

    @Query(value = "SELECT * FROM WHISKY_POST WHERE user_id = ?1 ORDER BY rating ASC LIMIT 10 OFFSET ?2 * 10",nativeQuery = true)
    List<WhiskyPost> getByRatingDescend(Long userId, int page);

    @Query(value = "SELECT COUNT(*) FROM WHISKY_POST WHERE user_id = ?1",nativeQuery = true)
    int getByUserId(Long userId);
}
