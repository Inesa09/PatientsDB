package ua.nulp.patientsDb.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 2, max = 15)
    private String username;

    @NotBlank
    @Size(min = 8, max = 24)
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 2, max = 30)
    private String fullName;
    @NotBlank
    private String numberPhone;

    private Set<String> role;
}
