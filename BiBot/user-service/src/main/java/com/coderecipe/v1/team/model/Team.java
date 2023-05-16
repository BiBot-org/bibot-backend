package com.coderecipe.v1.team.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.department.model.Department;
import com.coderecipe.v1.team.dto.TeamDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public static Team of(TeamDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Team.class);
    }
}
