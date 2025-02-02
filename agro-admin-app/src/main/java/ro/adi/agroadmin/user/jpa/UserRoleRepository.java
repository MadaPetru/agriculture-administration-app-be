package ro.adi.agroadmin.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.agroadmin.user.jpa.entity.UserRoleEntity;
import ro.adi.agroadmin.user.jpa.key.UserRoleKey;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleKey> {
    @Query(value = "SELECT DISTINCT userRole.id.role FROM UserRoleEntity userRole WHERE userRole.id.userId = :userId")
    Set<UserRole> findAllByUserId(Integer userId);

    @Modifying
    @Transactional
    void deleteAllById_UserId(Integer userId);
}
