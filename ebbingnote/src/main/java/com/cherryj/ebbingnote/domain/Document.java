package com.cherryj.ebbingnote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "document")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" ,"category"})
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter @Setter int id;

    @Column(name = "status", length = 128)
    private @Getter @Setter String status;

    @Column(name = "title", length = 128)
    private @Getter @Setter String title;

    @Column(columnDefinition = "text")
    private @Getter @Setter String content;

    @Transient
    private @Getter @Setter Integer categoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "fk_document_category_categoryid"),
            referencedColumnName = "id")
    private @Getter @Setter Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ownerId", foreignKey = @ForeignKey(name = "fk_document_useraccount_ownerid"),
            referencedColumnName = "id")
    private @Getter @Setter UserAccount owner;

    @Column(nullable = false)
    private @Getter @Setter Date createdDate;

    @Column
    private @Getter @Setter Date modifiedDate;

    @Column
    private @Getter @Setter Date reviewDate;

}
