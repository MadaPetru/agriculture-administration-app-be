package ro.adi.agroadmin.common.converter;

import org.mapstruct.Mapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.UserRequest;
import ro.adi.common.dto.request.UserRequestDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequest toUserRequest(UserRequestDto request);
}
