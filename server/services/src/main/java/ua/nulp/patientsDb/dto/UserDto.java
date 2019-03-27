package ua.nulp.patientsDb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nulp.patientsDb.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String numberPhone;

    private Set<RoleEntity> roles = new HashSet<RoleEntity>();

    public UserDto(Long id, String username, String password, String email, String fullName, String numberPhone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }
}