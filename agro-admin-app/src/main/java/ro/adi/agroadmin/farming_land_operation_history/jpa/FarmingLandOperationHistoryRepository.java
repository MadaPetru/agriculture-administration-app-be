package ro.adi.agroadmin.farming_land_operation_history.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.adi.agroadmin.farming_land_operation_history.jpa.entity.FarmingLandOperationHistoryEntity;

public interface FarmingLandOperationHistoryRepository extends JpaSpecificationExecutor<FarmingLandOperationHistoryEntity>, JpaRepository<FarmingLandOperationHistoryEntity, Integer> {
    void deleteAllByFarmingLandId(Integer farmingLandId);
}
