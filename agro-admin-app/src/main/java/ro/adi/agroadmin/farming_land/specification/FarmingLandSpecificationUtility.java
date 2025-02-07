package ro.adi.agroadmin.farming_land.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandSearchRequest;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandEntity;
import ro.adi.agroadmin.user.utils.UserUtils;
import ro.adi.farming_land.dto.request.FarmingLandSearchBy;

import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class FarmingLandSpecificationUtility {
    public static Specification<FarmingLandEntity> search(FarmingLandSearchRequest request) {
        return (farmingLandEntity, cq, cb) -> {
            var predicates = new ArrayList<Predicate>();
            predicates.add(getPredicateCreatedBy(farmingLandEntity, cb));
            getPredicateTitleLikeSearch(request, farmingLandEntity, cb).ifPresent(predicates::add);
            return cq.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    private static Optional<Predicate> getPredicateTitleLikeSearch(FarmingLandSearchRequest request, Root<FarmingLandEntity> farmingLandEntity, CriteriaBuilder cb) {
        return Optional.ofNullable(request.getSearchBy())
                .map(FarmingLandSearchBy::getTitleLikeSearch)
                .map(titleLikeSearch -> {
                    Path<String> titlePath = farmingLandEntity.get(FarmingLandEntity.TITLE_FILED_NAME);
                    var titleLikeExpression = "%" + request.getSearchBy().getTitleLikeSearch() + "%";
                    return cb.like(titlePath, titleLikeExpression);
                });
    }

    private static Predicate getPredicateCreatedBy(Root<FarmingLandEntity> farmingLandEntity, CriteriaBuilder cb) {
        Path<Integer> createdByPath = farmingLandEntity.get(FarmingLandEntity.CREATED_BY_FILED_NAME);
        return cb.equal(createdByPath, UserUtils.getIdOfCurrentUser());
    }
}