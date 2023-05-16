package com.coderecipe.v1.user.bibotuser.model.repository;

import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibotUserRepository extends JpaRepository<BibotUser, Long> {
}
