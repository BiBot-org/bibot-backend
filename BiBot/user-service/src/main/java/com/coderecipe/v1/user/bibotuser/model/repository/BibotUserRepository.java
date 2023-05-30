package com.coderecipe.v1.user.bibotuser.model.repository;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BibotUserRepository extends JpaRepository<BibotUser, UUID>,
        JpaSpecificationExecutor<BibotUser> {
    List<BibotUser> findAllByUserRoleInOrderByUserRole(List<UserRole> roles);

    Optional<BibotUser> findBibotUserByEmail(String email);

    Boolean existsBibotUserByUserRole(UserRole role);

    Boolean existsByEmail(String email);

    Page<BibotUser> findAll(Specification<BibotUser> spec, Pageable pageable);

}
