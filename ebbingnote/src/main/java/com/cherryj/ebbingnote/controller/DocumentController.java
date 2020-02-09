package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.Document;
import com.cherryj.ebbingnote.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController extends BaseController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("add")
    public Response<Document> add(Document document, HttpServletRequest request) {

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

        return documentService.create(document, getCurrentUserAccountId(request));
    }

    @PostMapping("modify")
    public Response<Document> modify(Document document) {

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
    public Response<Boolean> delete(Document document) {

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

    @PostMapping("list")
    public Response<List<Document>> list(Integer categoryId) {

        Response<List<Document>> response = new Response<>();
        if (categoryId == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        return documentService.listByCategoryId(categoryId);
    }

}
