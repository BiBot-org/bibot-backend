package com.coderecipe.v1.user.model.repository;

import com.coderecipe.v1.user.model.BibotUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibotUserRepository extends JpaRepository<BibotUser, Long> {
}
