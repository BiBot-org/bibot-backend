package com.coderecipe.v1.limitation.model.repository;

import com.coderecipe.v1.limitation.model.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILimitRepository extends JpaRepository<Limitation, Integer> {

}
