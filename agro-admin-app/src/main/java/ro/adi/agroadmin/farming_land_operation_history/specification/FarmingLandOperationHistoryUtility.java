package ro.adi.agroadmin.farming_land_operation_history.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.jpa.entity.FarmingLandOperationHistoryEntity;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchBy;

import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class FarmingLandOperationHistoryUtility {

    public static Specification<FarmingLandOperationHistoryEntity> search(FarmingLandOperationHistorySearchRequest request) {
        return (farmingLandOperationHistoryEntity, cq, cb) -> {
            var predicates = new ArrayList<Predicate>();
            getPredicateFarmingLandIdSearchEqual(request, farmingLandOperationHistoryEntity, cb).ifPresent(predicates::add);
            return cq.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    private static Optional<Predicate> getPredicateFarmingLandIdSearchEqual(FarmingLandOperationHistorySearchRequest request, Root<FarmingLandOperationHistoryEntity> farmingLandOperationHistoryEntity, CriteriaBuilder cb) {
        return Optional.ofNullable(request.getSearchBy())
                .map(FarmingLandOperationHistorySearchBy::getFarmingLandId)
                .map(farmingLandId -> {
                    var titlePath = farmingLandOperationHistoryEntity.get(FarmingLandOperationHistoryEntity.FARMING_LAND_ID_FILED_NAME);
                    return cb.equal(titlePath, farmingLandId);
                });
    }
}
