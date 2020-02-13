package com.cherryj.ebbingnote.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByOwnerOrderByCategoryName(UserAccount userAccount);

    Category findFirstByOwnerAndCategoryName(UserAccount userAccount, String categoryName);
}