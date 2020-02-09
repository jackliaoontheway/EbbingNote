package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Category;
import com.cherryj.ebbingnote.domain.CategoryRepository;
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
        result.add(reviewCategory);
        List<Category> list = categoryRepository.findByOwnerOrderByCategoryName(userAccountService.findById(userAccountId));
        if (list != null) {
            result.addAll(list);
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
    public Response<Category> create(Category category, Integer userAccountId) {
        Response<Category> response = new Response<Category>();

        if (checkCategoryExisted(category.getCategoryName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Category name is already existed");
            return response;
        }
        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setOwner(userAccountService.findById(userAccountId));
        newCategory.setCreatedDate(new Date());
        categoryRepository.save(newCategory);
        response.setData(newCategory);
        return response;
    }

    private boolean checkCategoryExisted(String categoryName) {
        return (categoryRepository.findFirstByCategoryName(categoryName)) != null;
    }
}
