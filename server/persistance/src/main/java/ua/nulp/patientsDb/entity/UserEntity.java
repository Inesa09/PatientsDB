package ua.nulp.patientsDb.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 30, nullable = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 15, unique = true)
    private String numberPhone;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();

    public UserEntity(String username, String password, String email, String fullName, String numberPhone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }

}
