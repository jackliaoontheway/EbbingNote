package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Category;
import com.cherryj.ebbingnote.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public Response<Category> add(Category category, HttpServletRequest request) {

        Response<Category> response = new Response<>();
        if (category == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(category.getCategoryName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter categoryName is null");
            return response;
        }

        return categoryService.create(category, getCurrentUserAccountId(request));
    }

    @PostMapping("modify")
    public Response<Category> modify(Category category) {

        Response<Category> response = new Response<>();
        if (category == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(category.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter category id is null");
            return response;
        }

        return categoryService.modify(category);
    }

    @PostMapping("delete")
    public Response<Boolean> delete(Category category) {

        Response<Boolean> response = new Response<>();
        if (category == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(category.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter category id is null");
            return response;
        }

        return categoryService.delete(category);
    }

    @PostMapping("list")
    public Response<List<Category>> list(HttpServletRequest request) {
        return categoryService.list(getCurrentUserAccountId(request));
    }

}
