package ro.adi.agroadmin.farming_land_operation_history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.farming_land_operation_history.converter.FarmingLandOperationHistoryMapper;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.jpa.FarmingLandOperationHistoryRepository;
import ro.adi.agroadmin.farming_land_operation_history.specification.FarmingLandOperationHistoryUtility;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandOperationHistoryServiceImpl implements FarmingLandOperationHistoryService {

    private final FarmingLandOperationHistoryMapper farmingLandOperationHistoryMapper;
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
    @Transactional
    public void deleteFarmingLandOperationHistoriesByFarmingLandId(Integer id) {
        farmingLandOperationHistoryRepository.deleteAllByFarmingLandId(id);
    }
}
