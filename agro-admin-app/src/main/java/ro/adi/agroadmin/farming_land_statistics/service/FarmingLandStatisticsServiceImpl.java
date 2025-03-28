package ro.adi.agroadmin.farming_land_statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_operation_history.jpa.FarmingLandOperationHistoryRepository;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsMapper;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandStatisticsRepository;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandsStatisticsPerOperationAndYearRepository;
import ro.adi.agroadmin.user.utils.UserUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmingLandStatisticsServiceImpl implements FarmingLandStatisticsService {

    private final FarmingLandStatisticsMapper farmingLandStatisticsMapper;
    private final FarmingLandStatisticsRepository farmingLandStatisticsRepository;
    private final FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository;
    private final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    private final FarmingLandsStatisticsPerOperationAndYearRepository farmingLandsStatisticsPerOperationAndYearRepository;

    @Override
    public List<FarmingLandsProfitabilityPerYearResponse> revenueAndCostsPerYear(Integer startYear, Integer endYear) {
        var user = UserUtils.getIdOfCurrentUser();
        var entities = farmingLandStatisticsRepository.findFarmingLandStatisticsPerYearEntitiesByCreatedByAndYearBetween(user, startYear, endYear);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponse(entities);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponse> getProfitabilityPerOperations(Integer startYear, Integer endYear) {
        var user = UserUtils.getIdOfCurrentUser();
        var entities = farmingLandsStatisticsPerOperationAndYearRepository.findAllByYearBetweenAndCreatedBy(startYear, endYear, user);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponse(entities);
    }

    @Override
    public void update(FarmingLandsProfitabilityPerYearUpdateRequest request) {
        if (!farmingLandOperationHistoryRepository.existsByYearAndCreatedBy(request.getYear(), request.getCreatedBy())) {
            farmingLandStatisticsRepository.deleteByYearAndCreatedBy(request.getYear(), request.getCreatedBy());
            return;
        }
        var entityToBeUpdated = farmingLandStatisticsMapper.toFarmingLandStatisticsPerYearEntity(request);
        var optionalEntity = farmingLandStatisticsRepository.findFarmingLandStatisticsPerYearEntityByYearAndCreatedBy(request.getYear(), request.getCreatedBy());
        if (optionalEntity.isPresent()) {
            var existentEntity = optionalEntity.get();
            entityToBeUpdated.setVersion(existentEntity.getVersion());
            entityToBeUpdated.setCost(entityToBeUpdated.getCost() + existentEntity.getCost());
            entityToBeUpdated.setRevenue(entityToBeUpdated.getRevenue() + existentEntity.getRevenue());
        }
        farmingLandStatisticsRepository.save(entityToBeUpdated);
    }
}
