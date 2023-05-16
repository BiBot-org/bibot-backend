package com.coderecipe.v1.user.bibotuser.dto;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BibotUserDTO {
    private UUID id;
    private String name;
    private UserRole userRole;
    private String profileUrl;
    private String email;
    private String duty;
}
