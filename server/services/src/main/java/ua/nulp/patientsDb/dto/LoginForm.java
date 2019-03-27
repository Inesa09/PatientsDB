package ua.nulp.patientsDb.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @NotBlank
    @Size(min = 2, max = 10)
    private String username;

    @NotBlank
    @Size(min = 8, max = 24)
    private String password;
}
