package lab.soa.domain.specifications.flat;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.service.filters.flat.FlatFilterField;

public class FlatIntervalSpecification implements FlatIntervalAndRangeSpecification {
    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFilterFieldName()
                    ),
                    fieldValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.lessThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                ),
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldNameEntity.getFilterFieldName()
                    ).get(fieldName.getFilterFieldName()),
                    fieldValue
                )
            );
    }
}
