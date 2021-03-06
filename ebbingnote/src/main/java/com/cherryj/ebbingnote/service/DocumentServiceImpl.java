package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.*;
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
    public Response<Document> create(Document document) {
        Response<Document> response = new Response<Document>();
        Category category = categoryService.findById(document.getCategoryId());
        if (category != null) {
            document.setCategory(category);
        } else {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Category is null");
            return response;
        }
        document.setCreatedDate(new Date());
        document.setOwner(category.getOwner());
        document = documentRepository.save(document);
        response.setData(document);
        return response;
    }

    @Override
    public Response<Document> modify(Document document) {
        Response<Document> response = new Response<Document>();
        Document existedDocument = documentRepository.getOne(document.getId());
        Category category = categoryService.findById(document.getCategoryId());
        if (category != null) {
            existedDocument.setCategory(category);
        } else {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Category is null");
            return response;
        }
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
    public Response<Document> findById(Integer id) {
        Response<Document> response = new Response<Document>();
        Document document = documentRepository.getOne(id);
        document.setCategoryId(document.getCategory().getId());
        response.setData(document);
        return response;
    }

    @Override
    public Response<List<Document>> listByCategoryId(UserAccount userAccount, Integer categoryId) {
        Response<List<Document>> response = new Response<List<Document>>();
        List<Document> result = new ArrayList<>();
        //  计算每日复习的 document
        if (categoryId == -1) {
            result = listReviewDocument(userAccount);
        } else {
            List<Document> list = documentRepository.findByOwnerAndCategoryOrderByModifiedDateDesc(userAccount, categoryService.findById(categoryId));
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

    private List<Document> listReviewDocument(UserAccount userAccount) {
        // 应该查出前 15天的note， 或者 这里使用日期查询 查多次
        Calendar calendarForQuery = Calendar.getInstance();
        calendarForQuery.add(Calendar.DAY_OF_MONTH, -16);
        List<Document> halfMonthDocuments = documentRepository.findByOwnerAndCreatedDateAfter(userAccount, calendarForQuery.getTime());

        List<Document> result = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        String today = getDateStr(calendar);
        // 获取当天,前第一天，前第二天，前第四天，前第七天，前第14天
        List<String> reviewDateStrList = new ArrayList<>();
        reviewDateStrList.add(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        reviewDateStrList.add(getDateStr(calendar));
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        reviewDateStrList.add(getDateStr(calendar));

        for (Document note : halfMonthDocuments) {
            if (DocumentStatus.DELETE.name().equals(note.getStatus())) {
                continue;
            }
            if (reviewDateStrList.contains(getDateStr(note.getCreatedDate()))) {
                // 如果状态是review 但是review 日期不是 当天的 将状态改为未复习
                if (DocumentStatus.REVIEWED.name().equals(note.getStatus()) && !today.equals(getDateStr(note.getReviewDate()))) {
                    note.setStatus(null);
                }
                result.add(note);
            }
        }
        return result;
    }

    private String getDateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String getDateStr(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }


}
