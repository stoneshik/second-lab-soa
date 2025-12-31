package lab.soa.repositories.specifications;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.bd.entities.Flat;

public interface MySpecification {
    Specification<Flat> createSpecification(
        FieldName fieldName,
        String fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        String fieldValue
    );

    Specification<Flat> createSpecification(
        FieldName fieldName,
        Integer fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Integer fieldValue
    );

    Specification<Flat> createSpecification(
        FieldName fieldName,
        Long fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Long fieldValue
    );

    Specification<Flat> createSpecification(
        FieldName fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecification(
        FieldName fieldName,
        LocalDateTime fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        LocalDateTime fieldValue
    );
}
