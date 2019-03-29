package ua.nulp.patientsDb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.nulp.patientsDb.dto.LoginForm;
import ua.nulp.patientsDb.dto.SignUpForm;
import ua.nulp.patientsDb.entity.RoleEntity;
import ua.nulp.patientsDb.entity.UserEntity;
import ua.nulp.patientsDb.repository.RoleRepository;
import ua.nulp.patientsDb.repository.UserRepository;
import ua.nulp.patientsDb.security.JwtResponse;
import ua.nulp.patientsDb.security.ResponseMessage;
import ua.nulp.patientsDb.security.TokenProvider;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        JwtResponse jwt2 = new JwtResponse(jwt, userDetail.getUsername(), userDetail.getAuthorities());
        return new ResponseEntity<>(jwt2, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail: Username is already exists!"), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getEmail(), registerRequest.getFullName(), registerRequest.getNumberPhone());
        Set<RoleEntity> roles = new HashSet<RoleEntity>();
        Set<String> strRole = registerRequest.getRole();
        strRole.forEach(role -> {
            RoleEntity roleEntity = roleRepository.findByName(role);
            roles.add(roleEntity);
        });
        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}
