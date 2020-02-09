package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.domain.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Integer categoryId);

    Response<List<Category>> list(Integer currentUserAccountId);

    Response<Boolean> delete(Category category);

    Response<Category> modify(Category category);

    Response<Category> create(Category category, Integer currentUserAccountId);
}
