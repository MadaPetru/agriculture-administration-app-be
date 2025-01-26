package ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.services.UpdateFarmingLandStatisticsPerYearAndOperationService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateFarmingLandStatisticsPerYearAndOperationEmitter {

    private final List<UpdateFarmingLandStatisticsPerYearAndOperationService> services;

    public void emitUpdate(UpdateFarmingLandStatisticsPerYearAndOperationScenario scenario, FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {
        log.info("Update statistics per operation and year for case: " + scenario.name());
        services.stream()
                .filter(service -> service.isType(scenario))
                .findFirst()
                .ifPresent((service) -> service.update(request));
    }

}
