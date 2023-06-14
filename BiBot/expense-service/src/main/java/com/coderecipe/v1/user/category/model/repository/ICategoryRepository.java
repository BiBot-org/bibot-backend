package com.coderecipe.v1.user.category.model.repository;

import com.coderecipe.v1.user.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String name);

    List<Category> findAllByOrderByIdAsc();

    Optional<Category> findByIdAndIsDeletedNot(Long id, boolean isDeleted);

    List<Category> findAllByIsDeletedNot(boolean isDeleted);
}
