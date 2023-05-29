package com.coderecipe.v1.user.bibotuser.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BibotUserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String profileUrl;
    private String email;
    private String duty;

    public static BibotUserDTO of(BibotUser entity) {
        return ModelMapperUtils.getModelMapper().map(entity, BibotUserDTO.class);
    }

    public static Page<BibotUserDTO> of(Page<BibotUser> entities) {
        return entities.map(BibotUserDTO::of);
    }
}
