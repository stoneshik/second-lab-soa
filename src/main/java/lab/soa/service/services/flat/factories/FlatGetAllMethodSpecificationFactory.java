package lab.soa.service.services.flat.factories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.flat.FlatIntervalAndRangeSpecification;
import lab.soa.domain.specifications.flat.factories.FlatIntervalAndRangeSpecificationFactory;
import lab.soa.domain.specifications.flat.factories.FlatSpecificationFactory;
import lab.soa.service.filters.flat.FlatFilterOperation;
import lab.soa.service.filters.flat.FlatFilterParam;

public class FlatGetAllMethodSpecificationFactory {
    public Specification<Flat> create(List<FlatFilterParam> filterParams) {
        Specification<Flat> specification = Specification.unrestricted();
        FlatSpecificationFactory flatSpecificationFactory = new FlatSpecificationFactory();
        FlatIntervalAndRangeSpecificationFactory flatIntervalAndRangeSpecificationFactory =
            new FlatIntervalAndRangeSpecificationFactory();
        for (FlatFilterParam flatFilterParam: filterParams) {
            FlatFilterOperation filterOperation = flatFilterParam.getOperation();
            if (filterOperation.isRangeOrInterval()) {
                FlatIntervalAndRangeSpecification flatIntervalAndRangeSpecification =
                    flatIntervalAndRangeSpecificationFactory.create(filterOperation);
                
                specification.and(flatIntervalAndRangeSpecificationFactory.create(filterOperation));
                continue;
            }
            specification.and();
        }
        return specification;
    }
}
