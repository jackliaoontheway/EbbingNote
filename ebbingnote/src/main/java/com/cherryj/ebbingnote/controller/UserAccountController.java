package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.domain.UserAccount;
import com.cherryj.ebbingnote.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/useraccount")
public class UserAccountController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("register")
    public Response<UserAccount> register(UserAccount userAccount) {

        Response<UserAccount> response = new Response<>();

        if (userAccount == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(userAccount.getUserName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter userName is null");
            return response;
        }

        if (StringUtils.isEmpty(userAccount.getPassword())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter password is null");
            return response;
        }

        return userAccountService.register(userAccount);
    }

    @PostMapping("login")
    public Response<UserAccount> login(UserAccount userAccount, HttpServletRequest request) {

        Response<UserAccount> response = new Response<>();

        if (userAccount == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }

        if (StringUtils.isEmpty(userAccount.getUserName())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter userName is null");
            return response;
        }

        if (StringUtils.isEmpty(userAccount.getPassword())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter password is null");
            return response;
        }

        response = userAccountService.login(userAccount);

        //TODO 需要验证 session
        if (response.getStatus() == null && response.getData() != null) {
            setCurrentUserAccountId(request,response.getData().getId());
        }

        return response;
    }

}
