package ua.nulp.patientsDb.service;

import ua.nulp.patientsDb.dto.UserDto;
import ua.nulp.patientsDb.entity.UserEntity;

import java.util.List;

public interface UserService {

    void createUser(UserEntity userEntity);

    void updateUserById(Long id, UserEntity userEntity);

    UserDto findByUsername(String userName);

    UserDto getUserById(Long id);

    void deleteById (Long id);

    List<UserDto> findAll();

    UserEntity findUser (String userName);

    UserEntity findByUserName(String username);

    UserEntity findById(Long id);

}
