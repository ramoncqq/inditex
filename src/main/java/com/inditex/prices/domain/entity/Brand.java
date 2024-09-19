package com.inditex.prices.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Brand {

    private Integer id;
    private String name;

}
