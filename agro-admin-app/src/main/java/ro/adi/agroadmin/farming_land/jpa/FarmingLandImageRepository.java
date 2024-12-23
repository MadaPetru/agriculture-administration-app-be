package ro.adi.agroadmin.farming_land.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandImageEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FarmingLandImageRepository extends JpaRepository<FarmingLandImageEntity, Integer> {

    List<FarmingLandImageEntity> findAllByFarmingLandIdAndAtBetweenAndAndCreatedBy(Integer farmingLandId, LocalDateTime startDate, LocalDateTime endDate, String createdBy);
}
