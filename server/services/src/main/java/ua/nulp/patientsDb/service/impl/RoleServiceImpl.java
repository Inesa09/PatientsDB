package ua.nulp.patientsDb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nulp.patientsDb.entity.RoleEntity;
import ua.nulp.patientsDb.repository.RoleRepository;
import ua.nulp.patientsDb.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createRole(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
    }

    @Override
    public List<RoleEntity> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public RoleEntity findById(Long id) {
        RoleEntity role = roleRepository.findById(id).get();
        return role;
    }

    @Override
    public void deleteById(Long id, RoleEntity roleEntity) {
        roleEntity.setId(id);
        roleRepository.save(roleEntity);

    }

    @Override
    public void updateRoleById(Long id, RoleEntity roleEntity) {

        roleEntity.setId(id);
        roleRepository.save(roleEntity);

    }

    @Override
    public RoleEntity findRoleByName(String name) {
        RoleEntity role = roleRepository.findByName(name);
        return role;
    }
}
