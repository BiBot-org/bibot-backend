package com.coderecipe.v1.user.model;

import com.coderecipe.global.constant.entity.BaseTimeEntity;
import com.coderecipe.v1.department.model.Department;
import com.coderecipe.v1.rank.model.Rank;
import com.coderecipe.v1.team.model.Team;
import com.coderecipe.v1.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Data
@Table(name = "bibot_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BibotUser extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @Column(name = "role", columnDefinition = "VARCHAR(10) NOT NULL")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "profile_url", columnDefinition = "VARCHAR(2048)")
    private String profileUrl;

    @Column(name = "email", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;

    @Column(name = "duty", columnDefinition = "VARCHAR(20) Not NULL")
    private String duty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private Rank rank;

}