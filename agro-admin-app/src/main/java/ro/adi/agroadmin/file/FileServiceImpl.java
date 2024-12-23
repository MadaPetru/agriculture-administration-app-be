package ro.adi.agroadmin.file;

import com.azure.storage.blob.BlobServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.config.BlobStorageProperties;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageBlobResponse;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final BlobServiceClient blobServiceClient;
    private final BlobStorageProperties blobStorageProperties;

    public void uploadBlob(Integer id, String content) {
        var blobClient = blobServiceClient
                .getBlobContainerClient(blobStorageProperties.getContainerName())
                .getBlobClient(id.toString())
                .getBlockBlobClient();
        var dataStream = new ByteArrayInputStream(content.getBytes());
        blobClient.upload(dataStream, content.length(), true);
    }

    public List<FarmingLandImageBlobResponse> listFiles(List<String> blobNames) {
        var result = new ArrayList<FarmingLandImageBlobResponse>();
        var blobContainerClient = blobServiceClient.getBlobContainerClient(blobStorageProperties.getContainerName());
        blobContainerClient.listBlobs()
                .forEach((blobItem) -> {
                    var fileName = blobItem.getName();
                    var content = blobContainerClient.getBlobClient(fileName)
                            .downloadContent().toString();
                    var file = new FarmingLandImageBlobResponse();
                    file.setFileName(fileName);
                    file.setContent(content);
                    result.add(file);
                });
        return result;
    }
}
