package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.domain.Category;
import com.cherryj.ebbingnote.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }
}
