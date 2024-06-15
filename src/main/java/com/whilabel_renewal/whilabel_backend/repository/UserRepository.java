package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE sns_id = ?",nativeQuery = true)
    User findBySnsId(String sns_id);

    @Query(value = "SELECT * FROM users WHERE nickname = ?",nativeQuery = true)
    User findByNickname(String nickname);
}
