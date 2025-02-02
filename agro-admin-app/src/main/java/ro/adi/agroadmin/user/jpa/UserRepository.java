package ro.adi.agroadmin.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE UserEntity user SET user.email = :email WHERE user.id = :id")
    void updateUserEmail(Integer id,String email);
}
