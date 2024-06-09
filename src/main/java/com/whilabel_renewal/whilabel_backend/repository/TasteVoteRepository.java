package com.whilabel_renewal.whilabel_backend.repository;

import com.whilabel_renewal.whilabel_backend.domain.TasteVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteVoteRepository extends JpaRepository<TasteVote,Long> {
}
