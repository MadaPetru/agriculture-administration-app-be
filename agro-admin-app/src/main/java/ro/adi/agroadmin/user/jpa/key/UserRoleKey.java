package ro.adi.agroadmin.user.jpa.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserRoleKey implements Serializable {

    @Column
    private Integer userId;

    @Column
    private String role;
}
