package com.inditex.prices.domain.aggreate;

import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PriceAggregate {

    private Price price;
    private Brand brand;

}
