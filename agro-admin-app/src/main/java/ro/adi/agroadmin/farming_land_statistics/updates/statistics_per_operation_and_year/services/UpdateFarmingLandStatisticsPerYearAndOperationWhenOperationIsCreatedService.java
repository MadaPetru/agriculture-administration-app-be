package ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.services;

import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.jpa.FarmingLandsStatisticsPerOperationAndYearRepository;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario;

@Service
public class UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationIsCreatedService extends UpdateFarmingLandStatisticsPerYearAndOperationService {

    protected UpdateFarmingLandStatisticsPerYearAndOperationWhenOperationIsCreatedService(FarmingLandStatisticsPerYearAndOperationMapper mapper, FarmingLandsStatisticsPerOperationAndYearRepository repository) {
        super(mapper, repository);
    }

    @Override
    public void update(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {
        updateStatistics(request);
    }

    @Override
    public boolean isType(UpdateFarmingLandStatisticsPerYearAndOperationScenario scenario) {
        return scenario.equals(UpdateFarmingLandStatisticsPerYearAndOperationScenario.SAVE_FIELD_OPERATION);
    }
}
