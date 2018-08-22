package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 27821927L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP) // should be javax.persistence.Temporal & javax.persistence.TemporalType;
    private Date createdOn = new Date();

    public Review() { }

    public Review(String comment, Date createdOn) {
        this.comment = comment;
        this.createdOn = createdOn;
    }

}
