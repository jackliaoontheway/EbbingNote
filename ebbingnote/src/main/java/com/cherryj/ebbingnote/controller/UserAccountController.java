package com.cherryj.ebbingnote.controller;

import com.cherryj.ebbingnote.domain.UserAccount;
import com.cherryj.ebbingnote.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useraccount")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(value = "/findUserAccount/{id}")
    public UserAccount test(@PathVariable("id") Integer id) {
        return userAccountService.findById(id);
    }


}
