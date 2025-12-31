package lab.soa.repositories.specifications.flat;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.bd.entities.Flat;
import lab.soa.repositories.specifications.FieldName;
import lab.soa.repositories.specifications.MySpecification;

public class GreaterThanOrEqualsSpecification implements MySpecification {
    public Specification<Flat> createSpecification(
        FieldName fieldName,
        String fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        String fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldNameEntity.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldNameEntity.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldNameEntity.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldNameEntity.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        LocalDateTime fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        LocalDateTime fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(
                    fieldNameEntity.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }
}
