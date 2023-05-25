package com.coderecipe.v1.user.team.model.repository;

import com.coderecipe.v1.user.team.model.Team;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByDepartmentId(Long departmentId);

    Optional<Team> findTeamByName(String name);

    boolean existsByName(String name);
}
