package com.inditex.prices.infrastructure.output.db.shared;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

@UtilityClass
public class SortBuilder {

    public static Sort buildSort(Sort.Direction direction, String nameField) {
        return Sort.by(direction, nameField);
    }
}
