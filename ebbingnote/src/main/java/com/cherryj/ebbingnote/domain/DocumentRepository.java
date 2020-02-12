package com.cherryj.ebbingnote.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    List<Document> findByOwnerAndCategoryOrderByModifiedDateDesc(UserAccount userAccount, Category category);

    List<Document> findByOwnerAndCreatedDateAfter(UserAccount userAccount, Date createdDate);
}