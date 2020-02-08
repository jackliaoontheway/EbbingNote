package com.cherryj.ebbingnote.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter int id;

    @Column(name = "categoryName", length = 128)
    private @Getter @Setter String categoryName;

    @ManyToOne
    @JoinColumn(name = "ownerId", foreignKey = @ForeignKey(name = "fk_category_useraccount_ownerid"),
            referencedColumnName = "id")
    private @Getter @Setter UserAccount owner;

    @Column(nullable = false)
    private @Getter @Setter Date createdDate;

    public Category() {
    }
}
