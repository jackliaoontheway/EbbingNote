package com.cherryj.ebbingnote.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "useraccount")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter @Setter int id;

    @Column(name = "userName", length = 128)
    private @Getter @Setter String userName;

    /**
     * 注册时从页面传过来的密码
     */
    @Transient
    private @Getter @Setter String password;

    @Column(name = "passwordSalt", length = 64, nullable = false)
    private @Getter @Setter String passwordSalt;

    @Column(name = "passwordHash", length = 128, nullable = false)
    private @Getter @Setter String passwordHash;

}
