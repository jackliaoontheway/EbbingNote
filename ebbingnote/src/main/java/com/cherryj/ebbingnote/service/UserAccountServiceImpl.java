package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.domain.UserAccount;
import com.cherryj.ebbingnote.domain.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount findById(Integer id) {
        return userAccountRepository.findById(id).get();
    }
}
