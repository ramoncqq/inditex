package com.inditex.prices.infrastructure.output.db.shared;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuerySpecificationsBuilder<T> {

    private final List<Specification<T>> specifications = new ArrayList<>();

    public QuerySpecificationsBuilder<T> with(String key, FilterOperator operation, Object value) {
        specifications.add(new QuerySpecification<>(
                SearchCriteria.builder()
                        .key(key)
                        .operation(operation)
                        .value(value)
                        .build()
        ));
        return this;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Specification<T> buildSpecifications(FilterOperator op) {
        Specification<T> result = specifications.get(0);
        specifications.remove(0);
        if (op.equals(FilterOperator.AND)) {
            for (Specification spec : specifications) {
                result = Specification.where(result).and(spec);
            }
        }

        return result;
    }

}