package ro.adi.agroadmin.file;

import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageBlobResponse;

import java.util.List;

public interface FileService {

    void uploadBlob(Integer id, String content);

    List<FarmingLandImageBlobResponse> listFiles(List<String> blobNames);
}
