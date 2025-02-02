package ro.adi.agroadmin.user.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;
import ro.adi.agroadmin.user.jpa.key.UserRoleKey;

@Getter
@Setter
@Entity
@Table(name = "user_role_entity")
public class UserRoleEntity extends BaseEntity {

    @EmbeddedId
    private UserRoleKey id;

}
