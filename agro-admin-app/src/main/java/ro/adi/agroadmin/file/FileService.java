package ro.adi.agroadmin.file;

import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageBlobResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;

import java.util.List;
import java.util.Map;

public interface FileService {

    void uploadBlob(Integer id, String content);

    List<FarmingLandImageBlobResponse> listFiles(Map<Integer, FarmingLandImageResponse> blobNames);

    void deleteFile(Integer id);
}
