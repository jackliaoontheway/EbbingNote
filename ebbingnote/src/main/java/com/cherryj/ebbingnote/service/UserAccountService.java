package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.domain.UserAccount;

public interface UserAccountService {
    Response<UserAccount> register(UserAccount userAccount);
}
