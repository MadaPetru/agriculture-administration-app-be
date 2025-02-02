package ro.adi.agroadmin.user.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.adi.agroadmin.common.entity.BaseEntity;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "user_entity")
public class UserEntity extends BaseEntity implements UserDetails {

    public static String EMAIL_FIELD_NAME = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(columnDefinition = "integer", updatable = false, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
