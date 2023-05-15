package com.coderecipe.v1.department.model;

import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.team.model.Team;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
@Builder
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Team> teams;

    public static Department of(DepartmentDTO dto) {
        return Department.builder()
            .id(dto.getId())
            .name(dto.getName())
            .build();
    }
}
