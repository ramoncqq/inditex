package com.inditex.prices.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class Price {

    private Integer productId;

    private Double price;

    private Integer priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
