package com.cherryj.ebbingnote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String loginUserAccountSessionAttributeName = "userAccountId";

    protected void setCurrentUserAccountId(HttpServletRequest request, Integer userAccountId) {
        HttpSession session = request.getSession();
        session.setAttribute(loginUserAccountSessionAttributeName, userAccountId);
    }

    protected Integer getCurrentUserAccountId(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(loginUserAccountSessionAttributeName);
    }


}
