package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.common.model.Request;
import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Category;
import com.cherryj.ebbingnote.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 360000)
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public Response<Category> add(@RequestBody Category category) {

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

        return categoryService.create(category);
    }

    @PostMapping("modify")
    public Response<Category> modify(@RequestBody Category category) {

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
    public Response<Boolean> delete(@RequestBody Category category) {

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
    public Response<List<Category>> list(@RequestBody Request<Integer> request) {
        Response<List<Category>> response = new Response<List<Category>>();
        if(request.getData() == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter user has't login in");
            return response;
        }
        return categoryService.list(request.getData());
    }

}
