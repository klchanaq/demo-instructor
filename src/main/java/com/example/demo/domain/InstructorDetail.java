package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail implements Serializable {

    private static final long serialVersionUID = 27821925L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String youtubeChannel;

    private String hobby;

    public InstructorDetail() {
    }

}
