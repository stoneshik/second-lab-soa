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
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lab.soa.domain.models.Coordinates;
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
    public Flat save(Flat flat) {
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
        return Optional.ofNullable(
            entityManager.find(Flat.class, id)
        );
    }

    @Override
    public List<Flat> findAll(
        Specification<Flat> specification,
        int offset,
        int limit,
        List<SortParam> sortParams
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flat> query = criteriaBuilder.createQuery(Flat.class);
        Root<Flat> root = query.from(Flat.class);
        root.fetch("coordinates", JoinType.INNER);
        root.fetch("house", JoinType.INNER);
        Join<Flat, Coordinates> coordinatesJoin = root.join("coordinates", JoinType.INNER);
        Join<Flat, House> houseJoin = root.join("house", JoinType.INNER);
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        if (sortParams != null && !sortParams.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortParam sortParam : sortParams) {
                addSortOrder(root, coordinatesJoin, houseJoin, orders, sortParam, criteriaBuilder);
            }
            query.orderBy(orders);
        }
        query.select(root);
        TypedQuery<Flat> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);
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
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Flat> root = query.from(Flat.class);
        query.select(criteriaBuilder.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public int deleteFirstByHouseCriteria(
        String houseName,
        Integer houseYear,
        Integer numberOfFlatsOnFloor
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flat> selectQuery = criteriaBuilder.createQuery(Flat.class);
        Root<Flat> root = selectQuery.from(Flat.class);
        Join<Flat, House> houseJoin = root.join("house", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        if (houseName != null) {
            predicates.add(
                criteriaBuilder.equal(
                    criteriaBuilder.lower(
                        houseJoin.get("name")
                    ),
                    houseName
                )
            );
        }
        if (houseYear != null) {
            predicates.add(
                criteriaBuilder.equal(
                    houseJoin.get("year"),
                    houseYear
                )
            );
        }
        if (numberOfFlatsOnFloor != null) {
            predicates.add(
                criteriaBuilder.equal(
                    houseJoin.get("numberOfFlatsOnFloor"),
                    numberOfFlatsOnFloor
                )
            );
        }
        if (!predicates.isEmpty()) {
            selectQuery.where(
                criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
                )
            );
        }
        selectQuery.orderBy(
            criteriaBuilder.asc(
                root.get("id")
            )
        );
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Flat> root = query.from(Flat.class);
        query.select(
            criteriaBuilder.sum(
                root.get("height").as(Long.class)
            )
        );
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

    private void addSortOrder(
        Root<Flat> root,
        Join<Flat, Coordinates> coordinatesJoin,
        Join<Flat, House> houseJoin,
        List<Order> orders,
        SortParam sortParam,
        CriteriaBuilder criteriaBuilder
    ) {
        String field = sortParam.getField();
        boolean ascending = sortParam.isAscending();
        if (field.startsWith("coordinates.")) {
            String coordinatesField = field.substring("coordinates.".length());
            addOrderFromPath(
                orders,
                coordinatesJoin,
                coordinatesField,
                ascending,
                criteriaBuilder
            );
        } else if (field.startsWith("house.")) {
            String houseField = field.substring("house.".length());
            addOrderFromPath(
                orders,
                houseJoin,
                houseField,
                ascending,
                criteriaBuilder
            );
        } else if (field.contains(".")) {
            addOrderFromPath(orders, root, field, ascending, criteriaBuilder);
        } else {
            if (ascending) {
                orders.add(
                    criteriaBuilder.asc(root.get(field))
                );
            } else {
                orders.add(
                    criteriaBuilder.desc(root.get(field))
                );
            }
        }
    }

    private void addOrderFromPath(
        List<Order> orders,
        From<?, ?> from,
        String fieldPath,
        boolean ascending,
        CriteriaBuilder criteriaBuilder
    ) {
        String[] pathParts = fieldPath.split("\\.");
        From<?, ?> currentFrom = from;
        for (int i = 0; i < pathParts.length - 1; i++) {
            currentFrom = currentFrom.join(pathParts[i], JoinType.INNER);
        }
        String finalField = pathParts[pathParts.length - 1];
        if (ascending) {
            orders.add(
                criteriaBuilder.asc(currentFrom.get(finalField))
            );
        } else {
            orders.add(
                criteriaBuilder.desc(currentFrom.get(finalField))
            );
        }
    }
}
