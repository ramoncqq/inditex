package com.inditex.prices.infrastructure.input.rest.price;

import com.inditex.prices.application.usecase.price.PriceFindUseCase;
import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.infrastructure.input.rest.shared.ApiDto;
import com.inditex.prices.infrastructure.input.rest.price.findprice.request.PriceFindRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inditex.prices.infrastructure.input.rest.shared.ApiDto.builderApiDto;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceFindUseCase priceFindUseCase;

    @PostMapping
    public ApiDto<List<PriceAggregate>> findPrice(@RequestBody @Valid PriceFindRequest priceFindRequest) {
        return builderApiDto(priceFindUseCase.execute(priceFindRequest));
    }

}
