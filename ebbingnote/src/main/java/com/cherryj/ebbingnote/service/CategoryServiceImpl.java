package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Category;
import com.cherryj.ebbingnote.domain.CategoryRepository;
import com.cherryj.ebbingnote.domain.Document;
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

    @Override
    public Category findById(Integer categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Override
    public Response<List<Category>> list(Integer userAccountId) {
        Response<List<Category>> response = new Response<List<Category>>();
        List<Category> result = new ArrayList();

        Category reviewCategory = new Category();
        reviewCategory.setId(-1);
        reviewCategory.setCategoryName("Review");
        Response<List<Document>> reviewDocumentResponse = documentService.listByCategoryId(-1);
        if (reviewDocumentResponse != null && reviewDocumentResponse.getData() != null) {
            reviewCategory.setDocumentList(reviewDocumentResponse.getData());
        }

        result.add(reviewCategory);

        List<Category> list = categoryRepository.findByOwnerOrderByCategoryName(userAccountService.findById(userAccountId));
        if (list != null) {
            result.addAll(list);
        }

        // 为了减少内容太多 将内容设空  单独通过id获取Document
        for (Category category : result) {
            List<Document> documentList = category.getDocumentList();
            if (documentList != null) {
                for (Document document : documentList) {
                    document.setContent(null);
                }
            }
        }

        response.setData(result);
        return response;
    }

    @Override
    public Response<Boolean> delete(Category category) {
        Response<Boolean> response = new Response<Boolean>();
        categoryRepository.delete(categoryRepository.getOne(category.getId()));
        return response;
    }

    @Override
    public Response<Category> modify(Category category) {
        Response<Category> response = new Response<Category>();
        if (checkCategoryExisted(category.getCategoryName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Category name is already existed");
            return response;
        }
        Category existedCategory = categoryRepository.getOne(category.getId());
        existedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existedCategory);
        response.setData(existedCategory);
        return response;
    }

    @Override
    public Response<Category> create(Category category) {
        Response<Category> response = new Response<Category>();

        if (checkCategoryExisted(category.getCategoryName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Category name is already existed");
            return response;
        }
        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setOwner(userAccountService.findById(category.getUserAccountId()));
        newCategory.setCreatedDate(new Date());
        categoryRepository.save(newCategory);
        response.setData(newCategory);
        return response;
    }

    private boolean checkCategoryExisted(String categoryName) {
        return (categoryRepository.findFirstByCategoryName(categoryName)) != null;
    }
}
