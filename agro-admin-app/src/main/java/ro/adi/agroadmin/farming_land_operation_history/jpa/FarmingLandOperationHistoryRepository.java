package ro.adi.agroadmin.farming_land_operation_history.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.farming_land_operation_history.jpa.entity.FarmingLandOperationHistoryEntity;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.ProfitabilityPerOperationView;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.ProfitabilityPerYearView;

import java.util.List;

public interface FarmingLandOperationHistoryRepository extends JpaSpecificationExecutor<FarmingLandOperationHistoryEntity>, JpaRepository<FarmingLandOperationHistoryEntity, Integer> {
    void deleteAllByFarmingLandId(Integer farmingLandId);

    @Query("select sum(entity.estimatedCost) as cost,sum(entity.estimatedRevenue) as revenue, year(entity.appliedAt) as year from FarmingLandOperationHistoryEntity as entity where year(entity.appliedAt) between :startYear and :endYear and entity.createdBy = :createdBy and entity.farmingLandId = :farmingLandId group by year(entity.appliedAt)")
    List<ProfitabilityPerYearView> revenueAndCostsPerYearForFarmingLand(String createdBy, Integer startYear, Integer endYear, Integer farmingLandId);

    @Query("select entity.operation as operation,sum(entity.estimatedCost) as cost,sum(entity.estimatedRevenue) as revenue from FarmingLandOperationHistoryEntity as entity where year(entity.appliedAt) between :startYear and :endYear and entity.createdBy = :createdBy and entity.farmingLandId = :farmingLandId group by entity.operation")
    List<ProfitabilityPerOperationView> getProfitabilityPerOperationsForFarmingLand(String createdBy, Integer startYear, Integer endYear, Integer farmingLandId);

    List<FarmingLandOperationHistoryEntity> findFarmingLandOperationHistoryEntitiesByFarmingLandId(Integer farmingLandId);

    @Query(value = "select count(*) > 0 from FarmingLandOperationHistoryEntity as operation where year(operation.appliedAt) = :year and operation.createdBy = :createdBy")
    boolean existsByYearAndCreatedBy(Integer year, String createdBy);

    @Query(value = "select count(*) > 0 from FarmingLandOperationHistoryEntity as operation where year(operation.appliedAt) = :year and operation.createdBy = :createdBy and operation.operation = :operation")
    boolean existsByYearAndCreatedByAndOperation(Integer year, String createdBy, OperationType operation);
}
