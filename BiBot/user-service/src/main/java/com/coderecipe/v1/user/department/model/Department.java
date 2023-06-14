package com.coderecipe.v1.user.department.model;

import com.coderecipe.v1.user.department.dto.DepartmentDTO;
import com.coderecipe.v1.user.team.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL", unique = true)
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Team> teams;

    public static Department of(DepartmentDTO dto) {
        return Department.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public static Department of(String departmentName) {
        return Department.builder()
                .name(departmentName)
                .build();
    }

    public void updateDepartmentInfo(DepartmentDTO dto) {
        this.name = dto.getName();
    }
}
