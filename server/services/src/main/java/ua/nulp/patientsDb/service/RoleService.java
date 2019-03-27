package ua.nulp.patientsDb.service;

import ua.nulp.patientsDb.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    void createRole(RoleEntity roleEntity);

    List<RoleEntity> findAll();

    RoleEntity findById(Long id);

    void deleteById(Long id, RoleEntity roleEntity);

    void updateRoleById(Long id, RoleEntity roleEntity);

    RoleEntity findRoleByName(String name);
}
