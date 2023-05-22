package com.coderecipe.v1.user.bibotuser.model.repository;

import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import org.springframework.data.jpa.domain.Specification;

public class BibotUserSpecification {

    private BibotUserSpecification() {
        throw new IllegalStateException("JPA Specification class");
    }

    public static Specification<BibotUser> equalDepartmentId(Long departmentId) {
        return (root, query, criteriabuilder) -> criteriabuilder.equal(root.get("team").get("department").get("id"), departmentId);
    }

    public static Specification<BibotUser> equalTeamId(Long teamId) {
        return (root, query, criteriabuilder) -> criteriabuilder.equal(root.get("team").get("id"), teamId);
    }

    public static Specification<BibotUser> equalRankId(Long rankId) {
        return (root, query, criteriabuilder) -> criteriabuilder.equal(root.get("rank").get("id"), rankId);
    }

    public static Specification<BibotUser> likeUsername(String name) {
        return (root, query, criteriabuilder) -> criteriabuilder.like(
                root.get("lastName"), "%" + name + "%");
    }


}
