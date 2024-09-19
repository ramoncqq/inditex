package com.inditex.prices.infrastructure.output.db.shared;

import jakarta.persistence.criteria.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;


@Builder
@Getter
public class QuerySpecification<T> implements Specification<T> {

    private final transient SearchCriteria criteria;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<?> fieldPath = fieldPath(root, criteria.getKey());
        Predicate predicate = null;

        // ESTO SE PODRÍA AMPLIAR Y AÑADIR MÁS CLAUSULAS COMO EL IN, BETWEEN ETC...

        switch (criteria.getOperation()) {
            case EQUAL:
                predicate = builder.equal(fieldPath, criteria.getValue());
                break;
            case LOWER_THAN_OR_EQUAL:
                predicate = builder.lessThanOrEqualTo((Path<Comparable>) fieldPath, (Comparable) criteria.getValue());
                break;
            case GREATER_THAN_OR_EQUAL:
                predicate = builder.greaterThanOrEqualTo((Path<Comparable>) fieldPath, (Comparable) criteria.getValue());
                break;
            default:
                break;
        }

        return predicate;
    }

    private <Z> Path<Z> fieldPath(Path<Z> root, String fieldName) {
        String[] fields = fieldName.split("\\.");
        Path<Z> result = root;
        for (String field : fields) {
            result = result.get(field);
        }
        return result;
    }
}
