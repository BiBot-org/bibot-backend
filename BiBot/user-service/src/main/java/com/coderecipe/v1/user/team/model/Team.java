package com.coderecipe.v1.user.team.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.team.vo.TeamAdminReq;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.team.dto.TeamDTO;
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

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public static Team of(TeamDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Team.class);
    }

    public static Team of(TeamAdminReq.CreateTeamReq req) {
        return ModelMapperUtils.getModelMapper().map(req, Team.class);
    }

    public void updateTeamName(TeamDTO dto) {
        this.name = dto.getName();
    }
}
