package ro.adi.agroadmin.farming_land.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandEntity;

import java.util.Optional;

@Repository
public interface FarmingLandRepository extends JpaSpecificationExecutor<FarmingLandEntity>, JpaRepository<FarmingLandEntity, Integer> {
    Optional<FarmingLandEntity> findByTitle(String title);

    @Query(value = "select coalesce(sum(area),0) from FarmingLandEntity where createdBy = :createdBy and areaUnitType = :areaUnitType")
    Float calculateTotalAreaByCreatedByAndAreaUnitType(Integer createdBy, AreaUnitType areaUnitType);
}
