package ro.adi.user.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.adi.user.dto.common.SessionDetailsResponseDto;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterResponseDto extends SessionDetailsResponseDto {
}
