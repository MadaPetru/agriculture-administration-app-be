package ro.adi.agroadmin.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BlobStorageConfig {

    private final BlobStorageProperties blobStorageProperties;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint(blobStorageProperties.getBlobEndpoint())
                .credential(new com.azure.storage.common.StorageSharedKeyCredential(blobStorageProperties.getAccountName(), blobStorageProperties.getAccountKey()))
                .buildClient();
    }
}