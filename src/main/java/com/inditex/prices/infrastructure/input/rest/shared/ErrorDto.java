package com.inditex.prices.infrastructure.input.rest.shared;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ErrorDto {

    private String message;

}
