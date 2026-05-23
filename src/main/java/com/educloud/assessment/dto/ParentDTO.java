package com.educloud.assessment.dto;

import com.educloud.assessment.entity.Parent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class ParentDTO {


    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private String relation;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;

    @NotBlank
    private String occupation;

    @NotBlank
    private String address;

    public static Parent toParent(ParentDTO parentDTO) {
        if (parentDTO == null) throw new  IllegalArgumentException("Request can not be null. Contact Support Team");

        Parent parent = new Parent();
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setRelation(parentDTO.getRelation());
        parent.setEmail(parentDTO.getEmail());
        parent.setPhone(parentDTO.getPhone());
        parent.setOccupation(parentDTO.getOccupation());
        parent.setAddress(parentDTO.getAddress());

        return parent;
    }
}
