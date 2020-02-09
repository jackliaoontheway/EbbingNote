package com.cherryj.ebbingnote.domain;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findByUserName(String userName);
}