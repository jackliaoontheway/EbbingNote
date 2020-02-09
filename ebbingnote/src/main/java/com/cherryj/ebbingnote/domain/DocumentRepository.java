package com.cherryj.ebbingnote.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    List<Document> findByCategoryOrderByModifiedDateDesc(Category category);
}