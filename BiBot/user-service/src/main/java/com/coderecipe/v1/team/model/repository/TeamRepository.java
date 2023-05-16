package com.coderecipe.v1.team.model.repository;

import com.coderecipe.v1.team.model.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByDepartmentId(Long departmentId);
    boolean existsByName(String name);
}
