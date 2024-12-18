package ro.adi.agroadmin.farming_land_statistics.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.FarmingLandsStatisticsPerOperationAndYearEntity;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.ProfitabilityPerOperationView;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmingLandsStatisticsPerOperationAndYearRepository extends JpaRepository<FarmingLandsStatisticsPerOperationAndYearEntity, Integer> {

    @Query("select entity.operation as operation,sum(entity.cost) as cost,sum(entity.revenue) as revenue from FarmingLandsStatisticsPerOperationAndYearEntity as entity where entity.year between :start and :from and entity.createdBy = :createdBy group by entity.operation")
    List<ProfitabilityPerOperationView> findAllByYearBetweenAndCreatedBy(Integer start, Integer from, String createdBy);

    int deleteByYearAndOperationAndCreatedBy(Integer year, OperationType operation, String createdBy);

    Optional<FarmingLandsStatisticsPerOperationAndYearEntity> findFarmingLandStatisticsPerYearEntityByYearAndCreatedByAndOperation(Integer year, String createdBy, OperationType operation);
}