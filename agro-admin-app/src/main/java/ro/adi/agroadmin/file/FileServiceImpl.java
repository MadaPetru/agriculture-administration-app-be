package ro.adi.agroadmin.file;

import com.azure.storage.blob.BlobServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ro.adi.agroadmin.config.BlobStorageProperties;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageBlobResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<FarmingLandImageBlobResponse> listFiles(Map<Integer, FarmingLandImageResponse> farmingLandImageResponseById) {
        var result = new ArrayList<FarmingLandImageBlobResponse>();
        if (CollectionUtils.isEmpty(farmingLandImageResponseById)) return result;
        var blobContainerClient = blobServiceClient.getBlobContainerClient(blobStorageProperties.getContainerName());
        blobContainerClient.listBlobs()
                .forEach((blobItem) -> {
                    var id = Integer.valueOf(blobItem.getName());
                    if (!farmingLandImageResponseById.containsKey(id)) return;
                    var farmingLandImageBlobResponse = farmingLandImageResponseById.get(id);
                    var fileName = farmingLandImageBlobResponse.getFileName();
                    var content = blobContainerClient.getBlobClient(blobItem.getName())
                            .downloadContent().toString();
                    var file = new FarmingLandImageBlobResponse();
                    file.setId(id);
                    file.setFileName(fileName);
                    file.setContent(content);
                    file.setAt(farmingLandImageBlobResponse.getAt());
                    result.add(file);
                });
        return result;
    }

    @Override
    public void deleteFile(Integer id) {
        var blobClient = blobServiceClient
                .getBlobContainerClient(blobStorageProperties.getContainerName())
                .getBlobClient(id.toString())
                .getBlockBlobClient();
        blobClient.delete();
    }
}
