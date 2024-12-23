package ro.adi.agroadmin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "azure.storage")
public class BlobStorageProperties {

    private String blobEndpoint;
    private String accountName;
    private String accountKey;
    private String containerName;
}
