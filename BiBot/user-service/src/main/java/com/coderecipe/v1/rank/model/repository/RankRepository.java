package com.coderecipe.v1.rank.model.repository;

import com.coderecipe.v1.rank.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    boolean existsByName(String name);
}
