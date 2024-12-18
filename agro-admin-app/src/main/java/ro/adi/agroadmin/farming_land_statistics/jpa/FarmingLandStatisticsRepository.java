package ro.adi.agroadmin.farming_land_statistics.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.FarmingLandsStatisticsPerYearEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmingLandStatisticsRepository extends JpaSpecificationExecutor<FarmingLandsStatisticsPerYearEntity>, JpaRepository<FarmingLandsStatisticsPerYearEntity, Integer> {

    int deleteByYearAndCreatedBy(Integer year, String createdBy);

    List<FarmingLandsStatisticsPerYearEntity> findFarmingLandStatisticsPerYearEntitiesByCreatedByAndYearBetween(String createdBy, Integer startYear, Integer endYear);

    Optional<FarmingLandsStatisticsPerYearEntity> findFarmingLandStatisticsPerYearEntityByYearAndCreatedBy(Integer year, String createdBy);
}
