package ro.adi.agroadmin.farming_land.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandImageEntity;

import java.time.LocalDateTime;

@Repository
public interface FarmingLandImageRepository extends JpaRepository<FarmingLandImageEntity, Integer> {

    Page<FarmingLandImageEntity> findAllByFarmingLandIdAndCreatedByAndAtBetweenOrderByAtDesc(
            Integer farmingLandId,
            Integer createdBy,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
