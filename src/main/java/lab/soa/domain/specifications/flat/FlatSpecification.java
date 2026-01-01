package lab.soa.domain.specifications.flat;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.service.filters.flat.FlatFilterField;

public interface FlatSpecification {
    Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        String fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        String fieldValue
    );

    Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Integer fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Integer fieldValue
    );

    Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Long fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Long fieldValue
    );

    Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        Float fieldValue
    );

    Specification<Flat> createSpecification(
        FlatFilterField fieldName,
        LocalDateTime fieldValue
    );

    Specification<Flat> createSpecificationFromEntity(
        FlatFilterField fieldNameEntity,
        FlatFilterField fieldName,
        LocalDateTime fieldValue
    );
}
