package com.coderecipe.v1.user.team.model.repository;

import com.coderecipe.v1.user.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByDepartmentId(Long departmentId);

    Optional<Team> findTeamByName(String name);

    boolean existsByName(String name);
}
