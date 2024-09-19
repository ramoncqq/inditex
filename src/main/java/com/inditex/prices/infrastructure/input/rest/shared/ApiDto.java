package com.inditex.prices.infrastructure.input.rest.shared;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Builder
@Getter
@ToString
public class ApiDto<T> {

    private T data;
    private List<ErrorDto> errors;

    public static ApiDto builderApiDto(Object data) {
        return ApiDto.builder()
                .data(data)
                .errors(Collections.emptyList())
                .build();
    }

}
