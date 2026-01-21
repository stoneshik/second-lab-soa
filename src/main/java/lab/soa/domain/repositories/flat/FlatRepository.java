package lab.soa.domain.repositories.flat;

import java.util.List;
import java.util.Optional;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.Specification;
import lab.soa.presentation.params.sort.SortParam;

public interface FlatRepository {
    Flat create(Flat flat);
    Flat update(Flat flat);
    void delete(Flat flat);
    Optional<Flat> findById(Long id);
    List<Flat> findAll(
        Specification<Flat> specification,
        int offset,
        int limit,
        List<SortParam> sortParams
    );
    long count(Specification<Flat> spec);
    int deleteFirstByHouseCriteria(
        String houseName,
        Integer houseYear,
        Integer numberOfFlatsOnFloor
    );
    Long sumAllHeights();
    List<HeightGroupProjection> getHeightDistributionByCount();
}
