package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.domain.UserAccount;

public interface UserAccountService {
    UserAccount findById(Integer id);
}
