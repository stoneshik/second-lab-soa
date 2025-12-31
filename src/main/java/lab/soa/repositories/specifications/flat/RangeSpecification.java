package lab.soa.repositories.specifications.flat;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.bd.entities.Flat;
import lab.soa.repositories.specifications.FieldName;

public class RangeSpecification implements IntervalAndRangeSpecification {
    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get(
                        fieldNameEntity.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldValue
                )
            );
    }
}
