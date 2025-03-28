package ro.adi.agroadmin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secretKey;
    private String valueForPasswordReset;
    private long expirationTime;
}
