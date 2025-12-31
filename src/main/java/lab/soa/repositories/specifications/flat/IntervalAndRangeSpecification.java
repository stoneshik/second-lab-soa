package lab.soa.repositories.specifications.flat;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.bd.entities.Flat;
import lab.soa.repositories.specifications.FieldName;

public interface IntervalAndRangeSpecification {
    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Integer fieldValue
    );

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Integer fieldValue
    );

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Long fieldValue
    );

    public Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Long fieldValue
    );

    public Specification<Flat> createSpecification(
        FieldName fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FieldName fieldNameEntity,
        FieldName fieldName,
        Float fieldValue
    );
}
