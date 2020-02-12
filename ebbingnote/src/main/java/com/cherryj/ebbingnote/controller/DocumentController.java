package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.common.model.Request;
import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Document;
import com.cherryj.ebbingnote.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 360000)
@RestController
@RequestMapping("/document")
public class DocumentController extends BaseController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("add")
    public Response<Document> add(@RequestBody Document document) {

        Response<Document> response = new Response<>();
        if (document == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(document.getTitle())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter document title is null");
            return response;
        }

        if(document.getCategoryId() == null || document.getCategoryId() == -1) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter categoryId error");
            return response;
        }

        return documentService.create(document);
    }

    @PostMapping("modify")
    public Response<Document> modify(@RequestBody Document document) {

        Response<Document> response = new Response<>();
        if (document == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(document.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter document id is null");
            return response;
        }

        return documentService.modify(document);
    }

    @PostMapping("delete")
    public Response<Boolean> delete(@RequestBody Document document) {

        Response<Boolean> response = new Response<>();
        if (document == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(document.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter document id is null");
            return response;
        }

        return documentService.delete(document);
    }

    @PostMapping("review")
    public Response<Document> review(@RequestBody Document document) {
        Response<Document> response = new Response<>();
        if (document == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(document.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter document id is null");
            return response;
        }

        return documentService.review(document);
    }

    @PostMapping("detail")
    public Response<Document> detail(@RequestBody Request<Integer> request) {
        Response<Document> response = new Response<>();
        if (request.getData() == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        return documentService.findById(request.getData());
    }

}
