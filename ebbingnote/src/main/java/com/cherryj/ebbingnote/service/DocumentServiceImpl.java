package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.domain.Document;
import com.cherryj.ebbingnote.domain.DocumentRepository;
import com.cherryj.ebbingnote.domain.DocumentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public Response<Document> create(Document document, Integer userAccountId) {
        Response<Document> response = new Response<Document>();
        if(document.getCategoryId() != null) {
            document.setCategory(categoryService.findById(document.getCategoryId()));
        }
        document.setCreatedDate(new Date());
        document.setOwner(userAccountService.findById(userAccountId));
        document = documentRepository.save(document);
        response.setData(document);
        return response;
    }

    @Override
    public Response<Document> modify(Document document) {
        Response<Document> response = new Response<Document>();
        Document existedDocument = documentRepository.getOne(document.getId());
        existedDocument.setTitle(document.getTitle());
        existedDocument.setContent(document.getContent());
        existedDocument.setModifiedDate(new Date());
        documentRepository.save(existedDocument);
        response.setData(existedDocument);
        return response;
    }

    @Override
    public Response<Boolean> delete(Document document) {
        Response<Boolean> response = new Response<Boolean>();
        Document existedDocument = documentRepository.getOne(document.getId());
        existedDocument.setStatus(DocumentStatus.DELETE.name());
        existedDocument.setModifiedDate(new Date());
        documentRepository.save(existedDocument);
        response.setData(true);
        return response;
    }

    @Override
    public Response<List<Document>> listByCategoryId(Integer categoryId) {
        Response<List<Document>> response = new Response<List<Document>>();
        List<Document> list = documentRepository.findByCategoryOrderByModifiedDateDesc(categoryService.findById(categoryId));
        List<Document> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Document document : list) {
                if (!DocumentStatus.DELETE.name().equals(document.getStatus())) {
                    result.add(document);
                }
            }
        }
        response.setData(result);
        return response;
    }


}
