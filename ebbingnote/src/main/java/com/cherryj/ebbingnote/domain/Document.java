package com.cherryj.ebbingnote.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter int id;

    @Column(name = "status", length = 128)
    private @Getter @Setter String status;

    @Column(name = "title", length = 128)
    private @Getter @Setter String title;

    @Column(columnDefinition = "text")
    private @Getter @Setter String content;

    @Transient
    private @Getter @Setter Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "fk_document_category_categoryid"),
            referencedColumnName = "id")
    private @Getter @Setter Category category;

    @ManyToOne
    @JoinColumn(name = "ownerId", foreignKey = @ForeignKey(name = "fk_document_useraccount_ownerid"),
            referencedColumnName = "id")
    private @Getter @Setter UserAccount owner;

    @Column(nullable = false)
    private @Getter @Setter Date createdDate;

    @Column(nullable = false)
    private @Getter @Setter Date modifiedDate;

}
