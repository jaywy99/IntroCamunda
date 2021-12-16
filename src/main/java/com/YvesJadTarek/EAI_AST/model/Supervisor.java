package com.YvesJadTarek.EAI_AST.model;


import lombok.Data;
import org.camunda.bpm.model.cmmn.PlanItemTransition;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="supervisors")
public class Supervisor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

//    @Column(name="location")
//    private String professionalLocation;
}
