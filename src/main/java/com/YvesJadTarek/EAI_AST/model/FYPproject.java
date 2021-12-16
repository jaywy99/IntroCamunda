package com.YvesJadTarek.EAI_AST.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="projects")
public class FYPproject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String sponsor;

    @Column
    private String description;

    @Column
    private String outcomes;

    @Column
    private String prerequisites;

}