package lab.soa.domain.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@FunctionalInterface
public interface Specification<T> {
    Predicate toPredicate(
        Root<T> root,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    );

    static <T> Specification<T> unrestricted() {
        return (root, query, criteriaBuilder) -> null;
    }

    default Specification<T> and(Specification<T> other) {
        return (root, query, criteriaBuilder) -> {
            Predicate thisPredicate = this.toPredicate(root, query, criteriaBuilder);
            Predicate otherPredicate = other.toPredicate(root, query, criteriaBuilder);
            if (thisPredicate == null && otherPredicate == null) {
                return null;
            }
            if (thisPredicate == null) {
                return otherPredicate;
            }
            if (otherPredicate == null) {
                return thisPredicate;
            }
            return criteriaBuilder.and(thisPredicate, otherPredicate);
        };
    }
}
