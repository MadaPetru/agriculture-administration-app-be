package ro.adi.agroadmin.farming_land_operation_history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.common.exception.NotFoundException;
import ro.adi.agroadmin.farming_land_operation_history.converter.FarmingLandOperationHistoryMapper;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.jpa.FarmingLandOperationHistoryRepository;
import ro.adi.agroadmin.farming_land_operation_history.specification.FarmingLandOperationHistoryUtility;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsMapper;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;
import ro.adi.agroadmin.user.utils.UserUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandOperationHistoryServiceImpl implements FarmingLandOperationHistoryService {

    private final FarmingLandStatisticsMapper farmingLandStatisticsMapper;
    private final FarmingLandOperationHistoryMapper farmingLandOperationHistoryMapper;
    private final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    private final FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository;

    @Override
    @Transactional
    public void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequest request) {
        var entity = farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryEntity(request);
        farmingLandOperationHistoryRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequest request) {
        var entity = farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryEntity(request);
        farmingLandOperationHistoryRepository.save(entity);
    }

    @Override
    public PageImpl<FarmingLandOperationHistoryResponse> search(FarmingLandOperationHistorySearchRequest request) {
        var pageable = request.getPageable();
        var specification = FarmingLandOperationHistoryUtility.search(request);
        var entities = farmingLandOperationHistoryRepository.findAll(specification, pageable);
        return farmingLandOperationHistoryMapper.toPageImplFarmingLandOperationHistoryResponse(entities);
    }

    @Override
    @Transactional
    public void deleteFarmingLandOperationHistoryById(Integer id) {
        farmingLandOperationHistoryRepository.deleteById(id);
    }

    @Override
    public FarmingLandOperationHistoryResponse findFarmingLandOperationHistoryById(Integer id) {
        var entity = farmingLandOperationHistoryRepository.findById(id).orElseThrow(() -> NotFoundException.getFarmingLandNotFoundById(id));
        return farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryResponse(entity);
    }

    @Override
    public void deleteFarmingLandOperationHistoriesByFarmingLandId(Integer id) {
        farmingLandOperationHistoryRepository.deleteAllByFarmingLandId(id);
    }

    @Override
    public List<FarmingLandOperationHistoryResponse> findOperationHistoriesByFarmingLandId(Integer farmingLandId) {
        var entities = farmingLandOperationHistoryRepository.findFarmingLandOperationHistoryEntitiesByFarmingLandId(farmingLandId);
        return farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryResponses(entities);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponse> revenueAndCostsPerYearForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        var createdBy = UserUtils.getIdOfCurrentUser();
        var entities = farmingLandOperationHistoryRepository.revenueAndCostsPerYearForFarmingLand(createdBy, startYear, endYear, farmingLandId);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponseFromView(entities);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponse> getProfitabilityPerOperationsForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        var createdBy = UserUtils.getIdOfCurrentUser();
        var entities = farmingLandOperationHistoryRepository.getProfitabilityPerOperationsForFarmingLand(createdBy, startYear, endYear, farmingLandId);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponse(entities);
    }
}
