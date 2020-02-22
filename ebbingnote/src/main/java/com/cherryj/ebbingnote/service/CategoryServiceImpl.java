package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private DocumentService documentService;

    private final static Integer REVIEW_CATEGORY_ID = -1;

    @Override
    public Category findById(Integer categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Override
    public Response<List<Category>> list(Integer userAccountId) {
        Response<List<Category>> response = new Response<List<Category>>();
        List<Category> result = new ArrayList();
        UserAccount userAccount = userAccountService.findById(userAccountId);

        Category reviewCategory = new Category();
        reviewCategory.setId(REVIEW_CATEGORY_ID);
        reviewCategory.setCategoryName("Review");
        Response<List<Document>> reviewDocumentResponse = documentService.listByCategoryId(userAccount, REVIEW_CATEGORY_ID);
        if (reviewDocumentResponse != null && reviewDocumentResponse.getData() != null) {
            reviewCategory.setDocumentList(reviewDocumentResponse.getData());
        }

        result.add(reviewCategory);

        List<Category> list = categoryRepository.findByOwnerOrderByCategoryName(userAccount);
        if (list != null) {
            for (Category category : list) {
                if (CategoryStatus.DELETE.name().equals(category.getStatus())) {
                    continue;
                }
                result.add(category);
            }
        }

        // 为了减少内容太多 将内容设空  单独通过id获取Document
        // 将DELETE 过滤掉
        for (Category category : result) {
            List<Document> documentList = category.getDocumentList();
            List<Document> filterDeletedList = new ArrayList<>();
            if (documentList != null) {
                for (Document document : documentList) {
                    if (DocumentStatus.DELETE.name().equals(document.getStatus())) {
                        continue;
                    }
                    document.setContent(null);
                    filterDeletedList.add(document);
                }
            }
            category.setDocumentList(filterDeletedList);
        }

        response.setData(result);
        return response;
    }

    @Override
    public Response<Boolean> delete(Category category) {
        Response<Boolean> response = new Response<Boolean>();
        Category existedCategory = categoryRepository.getOne(category.getId());
        existedCategory.setStatus(CategoryStatus.DELETE.name());
        existedCategory.setModifiedDate(new Date());
        categoryRepository.save(existedCategory);
        response.setData(true);
        return response;
    }

    @Override
    public Response<Category> modify(Category category) {
        Response<Category> response = new Response<Category>();

        UserAccount userAccount = userAccountService.findById(category.getUserAccountId());

        response = checkCategoryExisted(userAccount, category);
        if (response != null) {
            return response;
        }
        response = new Response<Category>();

        Category existedCategory = categoryRepository.getOne(category.getId());
        existedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existedCategory);
        response.setData(existedCategory);
        return response;
    }

    private Response<Category> checkCategoryExisted(UserAccount userAccount, Category category) {
        Response<Category> response = new Response<Category>();
        Category existedCategory = categoryRepository.findFirstByOwnerAndCategoryName(userAccount, category.getCategoryName());
        if (existedCategory != null) {
            if (CategoryStatus.DELETE.name().equals(existedCategory.getStatus())) {
                existedCategory.setStatus(CategoryStatus.RESET.name());
                categoryRepository.save(existedCategory);
                response.setData(existedCategory);
                return response;
            } else {
                response.setStatus(ResponseStatus.RequestParameterError.name());
                response.setMsg("Category name is already existed");
                return response;
            }
        }
        return null;
    }

    @Override
    public Response<Category> create(Category category) {
        Response<Category> response = new Response<Category>();

        UserAccount userAccount = userAccountService.findById(category.getUserAccountId());

        response = checkCategoryExisted(userAccount, category);
        if (response != null) {
            return response;
        }
        response = new Response<Category>();

        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setOwner(userAccount);
        newCategory.setCreatedDate(new Date());
        categoryRepository.save(newCategory);

        response.setData(newCategory);
        return response;
    }
}
