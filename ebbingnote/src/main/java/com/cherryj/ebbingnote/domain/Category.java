package com.cherryj.ebbingnote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter @Setter int id;

    @Column(name = "status", length = 128)
    private @Getter @Setter String status;

    @Column(name = "categoryName", length = 128)
    private @Getter @Setter String categoryName;

    @Transient
    private @Getter @Setter Integer userAccountId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ownerId", foreignKey = @ForeignKey(name = "fk_category_useraccount_ownerid"),
            referencedColumnName = "id")
    private @Getter @Setter UserAccount owner;

    @Column(nullable = false)
    private @Getter @Setter Date createdDate;

    @Column
    private @Getter @Setter Date modifiedDate;

    @OneToMany(mappedBy = "category")
    private @Getter @Setter List<Document> documentList;

    public Category() {
    }
}
