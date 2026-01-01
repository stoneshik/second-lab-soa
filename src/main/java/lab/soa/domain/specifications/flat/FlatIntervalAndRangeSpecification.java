package lab.soa.domain.specifications.flat;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.service.filters.flat.FlatFilterField;

public interface FlatIntervalAndRangeSpecification {
    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Integer fieldValue
    );

    public Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Integer fieldValue
    );

    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Long fieldValue
    );

    public Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Long fieldValue
    );

    public Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Float fieldValue
    );
}
