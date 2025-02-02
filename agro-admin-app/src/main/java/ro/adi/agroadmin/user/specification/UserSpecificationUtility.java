package ro.adi.agroadmin.user.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ro.adi.agroadmin.user.dto.request.UserSearchRequest;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;
import ro.adi.user.dto.request.UserSearchBy;

import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class UserSpecificationUtility {
    public static Specification<UserEntity> search(UserSearchRequest request) {
        return (userEntity, cq, cb) -> {
            var predicates = new ArrayList<Predicate>();
            getPredicateEmailLikeSearch(request, userEntity, cb).ifPresent(predicates::add);
            return cq.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    private static Optional<Predicate> getPredicateEmailLikeSearch(UserSearchRequest request, Root<UserEntity> farmingLandEntity, CriteriaBuilder cb) {
        return Optional.ofNullable(request.getSearchBy())
                .map(UserSearchBy::getUsernameLikeSearch)
                .map(titleLikeSearch -> {
                    Path<String> titlePath = farmingLandEntity.get(UserEntity.EMAIL_FIELD_NAME);
                    var titleLikeExpression = "%" + request.getSearchBy().getUsernameLikeSearch() + "%";
                    return cb.like(titlePath, titleLikeExpression);
                });
    }
}