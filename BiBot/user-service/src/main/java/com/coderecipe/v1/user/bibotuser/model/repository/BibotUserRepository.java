package com.coderecipe.v1.user.bibotuser.model.repository;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BibotUserRepository extends JpaRepository<BibotUser, UUID> {
    List<BibotUser> findAllByUserRoleInOrderByUserRole(List<UserRole> roles);
}
