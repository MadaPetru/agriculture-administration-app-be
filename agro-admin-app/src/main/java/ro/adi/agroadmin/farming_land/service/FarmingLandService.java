package ro.adi.agroadmin.farming_land.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ro.adi.agroadmin.farming_land.dto.request.*;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandResponse;

import java.util.Map;

public interface FarmingLandService {

    Float getAreaInHaOfFieldsAdministrated();

    PageImpl<FarmingLandResponse> search(FarmingLandSearchRequest requestDto);

    void saveFarmingLand(FarmingLandSaveRequest request);

    void updateFarmingLand(FarmingLandUpdateRequest request);

    void deleteFarmingLandById(Integer id);

    FarmingLandResponse findFarmingLandByTitle(String title);

    Map<Integer, String> uploadFile(UploadFieldImageRequest request, Integer farmingLandId);

    Page<FarmingLandImageResponse> listFiles(ListFieldImageRequest request, Integer farmingLandId);

    void deleteFile(Integer id);
}
