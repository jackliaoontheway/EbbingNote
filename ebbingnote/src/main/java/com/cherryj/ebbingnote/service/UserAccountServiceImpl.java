package com.cherryj.ebbingnote.service;

import com.cherryj.ebbingnote.common.model.Response;
import com.cherryj.ebbingnote.common.model.ResponseStatus;
import com.cherryj.ebbingnote.common.utils.CryptoUtil;
import com.cherryj.ebbingnote.domain.UserAccount;
import com.cherryj.ebbingnote.domain.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public Response<UserAccount> register(UserAccount userAccount) {

        Response<UserAccount> response = new Response<>();

        UserAccount existedUserAccount = userAccountRepository.findByUserName(userAccount.getUserName());
        if (existedUserAccount != null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("This username is already existed.");
            return response;
        }

        UserAccount registerUserAccount = new UserAccount();
        registerUserAccount.setUserName(userAccount.getUserName());
        registerUserAccount.setPasswordSalt(CryptoUtil.generateSalt());
        registerUserAccount.setPasswordHash(CryptoUtil.hashPassword(userAccount.getPassword(), registerUserAccount.getPasswordSalt()));

        registerUserAccount = userAccountRepository.save(registerUserAccount);
        response.setData(registerUserAccount);
        return response;
    }
}
