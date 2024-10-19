package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "body_part")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "physical_exam_id")
    private PhysicalExam physicalExam;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodyPart")
    private List<SubPart> subParts;

}
