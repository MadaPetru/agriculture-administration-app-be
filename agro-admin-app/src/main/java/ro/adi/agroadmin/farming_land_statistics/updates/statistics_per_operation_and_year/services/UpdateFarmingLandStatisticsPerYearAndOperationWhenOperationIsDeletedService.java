package ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.services;

import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_operation_history.jpa.FarmingLandOperationHistoryRepository;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandsStatisticsPerOperationAndYearRepository;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario;

@Service
public class UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationIsDeletedService extends UpdateFarmingLandStatisticsPerYearAndOperationService {

    private final FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository;

    protected UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationIsDeletedService(FarmingLandStatisticsPerYearAndOperationMapper mapper, FarmingLandsStatisticsPerOperationAndYearRepository repository, FarmingLandOperationHistoryRepository farmingLandOperationHistoryRepository) {
        super(mapper, repository);
        this.farmingLandOperationHistoryRepository = farmingLandOperationHistoryRepository;
    }

    @Override
    public void update(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {
        if (!farmingLandOperationHistoryRepository.existsByYearAndCreatedByAndOperation(request.getYear(), request.getCreatedBy(), request.getOperation())) {
            farmingLandsStatisticsPerOperationAndYearRepository.deleteByYearAndOperationAndCreatedBy(request.getYear(), request.getOperation(), request.getCreatedBy());
            return;
        }
        updateStatistics(request);
    }

    @Override
    public boolean isType(UpdateFarmingLandStatisticsPerYearAndOperationScenario scenario) {
        return scenario.equals(UpdateFarmingLandStatisticsPerYearAndOperationScenario.DELETE_FIELD) ||
                scenario.equals(UpdateFarmingLandStatisticsPerYearAndOperationScenario.DELETE_FIELD_OPERATION);
    }
}
