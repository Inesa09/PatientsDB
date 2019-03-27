package ua.nulp.patientsDb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nulp.patientsDb.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    UserEntity getUserById(Long id);

    List<UserEntity> findAll();

    UserEntity getByUsername(String username);

    @Query(value = "select * from user as u " +
            "left join user_roles as ur on u.id = ur.user_id " +
            "left join role as r on ur.role_id = r.id " +
            "where r.name = :name", nativeQuery = true)
    List<UserEntity> getUserEntitiesByRoleName(@Param("name") String name);

}
