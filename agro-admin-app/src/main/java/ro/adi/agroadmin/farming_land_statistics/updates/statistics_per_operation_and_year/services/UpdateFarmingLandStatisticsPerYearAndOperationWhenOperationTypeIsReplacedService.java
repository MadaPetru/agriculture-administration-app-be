package ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.services;

import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_operation_history.jpa.FarmingLandOperationHistoryRepository;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandsStatisticsPerOperationAndYearRepository;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario;

@Service
public class UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationTypeIsReplacedService extends UpdateFarmingLandStatisticsPerYearAndOperationService {

    private final FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository;

    protected UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationTypeIsReplacedService(FarmingLandStatisticsPerYearAndOperationMapper mapper, FarmingLandsStatisticsPerOperationAndYearRepository repository, FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository) {
        super(mapper, repository);
        this.farmingLandOperationHistoryRepository = farmingLandOperationHistoryRepository;
    }

    @Override
    public void update(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {
        var operationsOfTheTypeReplaced = farmingLandOperationHistoryRepository.countByYearAndCreatedByAndOperation(request.getYear(), request.getCreatedBy(), request.getOperation());
        if (operationsOfTheTypeReplaced == 1) {
            farmingLandsStatisticsPerOperationAndYearRepository.deleteByYearAndOperationAndCreatedBy(request.getYear(), request.getOperation(), request.getCreatedBy());
            return;
        }
        updateStatistics(request);
    }

    @Override
    public boolean isType(UpdateFarmingLandStatisticsPerYearAndOperationScenario scenario) {
        return scenario.equals(UpdateFarmingLandStatisticsPerYearAndOperationScenario.REPLACE_OPERATION_TYPE_WHEN_UPDATE_FIELD_OPERATION);
    }
}
