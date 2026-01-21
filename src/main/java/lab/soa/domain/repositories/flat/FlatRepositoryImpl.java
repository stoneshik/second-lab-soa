package lab.soa.domain.repositories.flat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.domain.specifications.Specification;
import lab.soa.presentation.params.sort.SortParam;

@ApplicationScoped
public class FlatRepositoryImpl implements FlatRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Flat create(Flat flat) {
        entityManager.persist(flat);
        entityManager.flush();
        return flat;
    }

    @Override
    public Flat update(Flat flat) {
        return entityManager.merge(flat);
    }

    @Override
    public void delete(Flat flat) {
        Flat managedFlat = entityManager.merge(flat);
        entityManager.remove(managedFlat);
    }

    @Override
    public Optional<Flat> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Flat.class, id));
    }

    @Override
    public List<Flat> findAll(
        Specification<Flat> specification,
        int offset,
        int limit,
        List<SortParam> sortParams
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flat> query = cb.createQuery(Flat.class);
        Root<Flat> root = query.from(Flat.class);
        root.fetch("coordinates", JoinType.INNER);
        root.fetch("house", JoinType.INNER);
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, cb);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        if (sortParams != null && !sortParams.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortParam sortParam : sortParams) {
                if (sortParam.isAscending()) {
                    orders.add(cb.asc(root.get(sortParam.getField())));
                } else {
                    orders.add(cb.desc(root.get(sortParam.getField())));
                }
            }
            query.orderBy(orders);
        }
        query.select(root).distinct(true);
        TypedQuery<Flat> typedQuery = entityManager.createQuery(query);
        if (offset >= 0) {
            typedQuery.setFirstResult(offset);
        }
        if (limit > 0) {
            typedQuery.setMaxResults(limit);
        }
        return typedQuery.getResultList();
    }

    @Override
    public long count(Specification<Flat> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Flat> root = query.from(Flat.class);
        query.select(criteriaBuilder.count(root));
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public int deleteFirstByHouseCriteria(
        String houseName,
        Integer houseYear,
        Integer numberOfFlatsOnFloor
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flat> selectQuery = cb.createQuery(Flat.class);
        Root<Flat> root = selectQuery.from(Flat.class);
        Join<Flat, House> houseJoin = root.join("house", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        if (houseName != null) {
            predicates.add(cb.equal(cb.lower(houseJoin.get("name")), houseName));
        }
        if (houseYear != null) {
            predicates.add(cb.equal(houseJoin.get("year"), houseYear));
        }
        if (numberOfFlatsOnFloor != null) {
            predicates.add(cb.equal(houseJoin.get("numberOfFlatsOnFloor"), numberOfFlatsOnFloor));
        }
        if (!predicates.isEmpty()) {
            selectQuery.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        selectQuery.orderBy(cb.asc(root.get("id")));
        TypedQuery<Flat> findQuery = entityManager.createQuery(selectQuery);
        findQuery.setMaxResults(1);
        Flat flatToDelete = findQuery.getResultStream().findFirst().orElse(null);
        if (flatToDelete != null) {
            entityManager.remove(entityManager.merge(flatToDelete));
            entityManager.flush();
            return 1;
        }
        return 0;
    }

    @Override
    public Long sumAllHeights() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Flat> root = query.from(Flat.class);
        query.select(cb.sum(root.get("height").as(Long.class)));
        Long result = entityManager.createQuery(query).getSingleResult();
        return result != null ? result : 0L;
    }

    @Override
    public List<HeightGroupProjection> getHeightDistributionByCount() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Flat> root = query.from(Flat.class);
        query.multiselect(
            root.get("height"),
            cb.count(root)
        ).groupBy(root.get("height"));
        List<Object[]> results = entityManager.createQuery(query).getResultList();
        List<HeightGroupProjection> projections = new ArrayList<>();
        for (Object[] result : results) {
            projections.add(new HeightGroupProjectionImpl(
                (Integer) result[0],
                (Long) result[1]
            ));
        }
        return projections;
    }
}
