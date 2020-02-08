package com.cherryj.ebbingnote.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "useraccount")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter int id;

    @Column(name = "userName", length = 128)
    private @Getter @Setter String userName;

    @Column(name = "passwordSalt", length = 64, nullable = false)
    private @Getter @Setter String passwordSalt;

    @Column(name = "passwordHash", length = 128, nullable = false)
    private @Getter @Setter String passwordHash;

}
