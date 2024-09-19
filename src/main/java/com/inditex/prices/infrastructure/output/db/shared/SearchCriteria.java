package com.inditex.prices.infrastructure.output.db.shared;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchCriteria {

    private String key;
    private FilterOperator operation;
    private Object value;
}
