package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.domain.Document;

import java.util.List;

public interface DocumentService {
    Response<Document> create(Document document, Integer userAccountId);

    Response<Document> modify(Document document);

    Response<List<Document>> listByCategoryId(Integer categoryId);

    Response<Boolean> delete(Document document);
}
