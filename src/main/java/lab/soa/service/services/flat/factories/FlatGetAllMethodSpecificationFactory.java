package lab.soa.service.services.flat.factories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.flat.FlatIntervalAndRangeSpecification;
import lab.soa.domain.specifications.flat.FlatSpecification;
import lab.soa.domain.specifications.flat.factories.FlatIntervalAndRangeSpecificationFactory;
import lab.soa.domain.specifications.flat.factories.FlatSpecificationFactory;
import lab.soa.service.filters.flat.FlatFilterOperation;
import lab.soa.service.filters.flat.FlatFilterParam;

public class FlatGetAllMethodSpecificationFactory {
    public Specification<Flat> create(List<FlatFilterParam> filterParams) {
        if (filterParams == null) {
            return Specification.unrestricted();
        }
        Specification<Flat> globalSpecification = null;
        FlatSpecificationFactory flatSpecificationFactory = new FlatSpecificationFactory();
        FlatIntervalAndRangeSpecificationFactory flatIntervalAndRangeSpecificationFactory =
            new FlatIntervalAndRangeSpecificationFactory();
        FlatSpecificationServiceFactory flatSpecificationServiceFactory =
            new FlatSpecificationServiceFactory();
        for (FlatFilterParam flatFilterParam: filterParams) {
            FlatFilterOperation filterOperation = flatFilterParam.getOperation();
            if (filterOperation.isRangeOrInterval()) {
                FlatIntervalAndRangeSpecification flatIntervalAndRangeSpecification =
                    flatIntervalAndRangeSpecificationFactory.create(filterOperation);
                Specification<Flat> localSpecification = flatSpecificationServiceFactory.create(
                    flatFilterParam,
                    flatIntervalAndRangeSpecification
                );
                if (globalSpecification == null) {
                    globalSpecification = localSpecification;
                    continue;
                }
                globalSpecification = globalSpecification.and(
                    flatSpecificationServiceFactory.create(
                        flatFilterParam,
                        flatIntervalAndRangeSpecification
                    )
                );
                continue;
            }
            FlatSpecification flatSpecification = flatSpecificationFactory.create(filterOperation);
            Specification<Flat> localSpecification = flatSpecificationServiceFactory.create(
                flatFilterParam,
                flatSpecification
            );
            if (globalSpecification == null) {
                globalSpecification = localSpecification;
                continue;
            }
            globalSpecification = globalSpecification.and(
                localSpecification
            );
        }
        return globalSpecification;
    }
}
