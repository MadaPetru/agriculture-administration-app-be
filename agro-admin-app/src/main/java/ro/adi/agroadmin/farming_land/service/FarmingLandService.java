package ro.adi.agroadmin.farming_land.service;

import org.springframework.data.domain.PageImpl;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandSaveRequest;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandSearchRequest;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandUpdateRequest;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandResponse;

public interface FarmingLandService {

    Float getAreaInHaOfFieldsAdministrated(String username);
    PageImpl<FarmingLandResponse> search(FarmingLandSearchRequest requestDto);
    void saveFarmingLand(FarmingLandSaveRequest request);
    void updateFarmingLand(FarmingLandUpdateRequest request);
    void deleteFarmingLandById(Integer id);
    FarmingLandResponse findFarmingLandByTitle(String title);
}
