package ua.nulp.patientsDb.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nulp.patientsDb.dto.UserDto;
import ua.nulp.patientsDb.entity.UserEntity;
import ua.nulp.patientsDb.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /* Get user by id */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Get all users */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*Get user by username */
    @GetMapping("/user/{username}/username")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        UserDto userDto = userService.findByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Delete user by id */
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, UserEntity userEntity) {
        userService.updateUserById(id, userEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}