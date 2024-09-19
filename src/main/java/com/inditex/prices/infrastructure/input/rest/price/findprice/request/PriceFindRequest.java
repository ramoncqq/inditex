package com.inditex.prices.infrastructure.input.rest.price.findprice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class PriceFindRequest {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer brandId;

    @NotNull
    private LocalDateTime startDate;

}
