package ro.adi.agroadmin.user.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;

@UtilityClass
public class UserUtils {

    public Integer getIdOfCurrentUser(){
        var principal = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }
}
