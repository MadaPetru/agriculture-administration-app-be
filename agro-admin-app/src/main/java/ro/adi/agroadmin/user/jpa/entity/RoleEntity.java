package ro.adi.agroadmin.user.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;
import ro.adi.agroadmin.common.entity.UserRole;

@Getter
@Setter
@Entity
@Table(name = "role_entity")
public class RoleEntity extends BaseEntity {

    @Id
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}
