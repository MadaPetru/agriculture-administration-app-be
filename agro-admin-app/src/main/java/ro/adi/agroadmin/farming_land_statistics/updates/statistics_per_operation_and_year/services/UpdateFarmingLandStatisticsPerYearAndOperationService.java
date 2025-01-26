package ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.services;

import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandsStatisticsPerOperationAndYearRepository;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario;

public abstract class UpdateFarmingLandStatisticsPerYearAndOperationService {

    abstract public void update(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request);

    abstract public boolean isType(UpdateFarmingLandStatisticsPerYearAndOperationScenario scenario);

    protected final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    protected final FarmingLandsStatisticsPerOperationAndYearRepository farmingLandsStatisticsPerOperationAndYearRepository;

    protected UpdateFarmingLandStatisticsPerYearAndOperationService(FarmingLandStatisticsPerYearAndOperationMapper mapper,
                                                                    FarmingLandsStatisticsPerOperationAndYearRepository repository) {
        this.farmingLandStatisticsPerYearAndOperationMapper = mapper;
        this.farmingLandsStatisticsPerOperationAndYearRepository = repository;
    }

    protected void updateStatistics(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {
        var entityToBeUpdated = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsStatisticsPerOperationAndYearEntityForSave(request);
        var optionalEntity = farmingLandsStatisticsPerOperationAndYearRepository.findFarmingLandStatisticsPerYearEntityByYearAndCreatedByAndOperation(
                request.getYear(), request.getCreatedBy(), request.getOperation());
        if (optionalEntity.isPresent()) {
            var existentEntity = optionalEntity.get();
            entityToBeUpdated.setVersion(existentEntity.getVersion());
            entityToBeUpdated.setCost(entityToBeUpdated.getCost() + existentEntity.getCost());
            entityToBeUpdated.setRevenue(entityToBeUpdated.getRevenue() + existentEntity.getRevenue());
        }
        farmingLandsStatisticsPerOperationAndYearRepository.save(entityToBeUpdated);
    }
}
