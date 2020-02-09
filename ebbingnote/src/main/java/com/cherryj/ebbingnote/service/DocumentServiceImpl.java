package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.domain.Document;
import com.cherryj.ebbingnote.domain.DocumentRepository;
import com.cherryj.ebbingnote.domain.DocumentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
        if (document.getCategoryId() != null) {
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
    public Response<Document> review(Document document) {
        Response<Document> response = new Response<Document>();
        Document existedDocument = documentRepository.getOne(document.getId());
        existedDocument.setStatus(DocumentStatus.REVIEWED.name());
        Date now = new Date();
        existedDocument.setReviewDate(now);
        existedDocument.setModifiedDate(now);
        documentRepository.save(existedDocument);
        response.setData(existedDocument);
        return response;
    }

    @Override
    public Response<List<Document>> listByCategoryId(Integer categoryId) {
        Response<List<Document>> response = new Response<List<Document>>();
        List<Document> result = new ArrayList<>();
        //  计算每日复习的 document
        if(categoryId == -1) {
            result = listReviewDocument();
        } else {
            List<Document> list = documentRepository.findByCategoryOrderByModifiedDateDesc(categoryService.findById(categoryId));
            if (list != null && list.size() > 0) {
                for (Document document : list) {
                    if (!DocumentStatus.DELETE.name().equals(document.getStatus())) {
                        result.add(document);
                    }
                }
            }
        }
        response.setData(result);
        return response;
    }

    private List<Document> listReviewDocument() {
        List<Document>  allDocuments = documentRepository.findAll();
        List<Document> result = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        // 获取当天,前第一天，前第二天，前第四天，前第七天，前第14天
        List<String> reviewDateStrList = new ArrayList<>();
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH,-2);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH,-4);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH,-14);
        reviewDateStrList.add(getDateStr(calendar));


        // 如果状态是review 但是review 日期不是 当天的 将状态改为未复习
        return result;
    }

    private String getDateStr(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }


}
