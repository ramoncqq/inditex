package com.inditex.prices.application.usecase.price;

import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.domain.repository.IPriceRepository;
import com.inditex.prices.infrastructure.input.rest.price.findprice.request.PriceFindRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PriceFindUseCase {

    private final IPriceRepository priceRepository;

    @Transactional
    public PriceAggregate execute(PriceFindRequest priceFindRequest) {
        return priceRepository.findPrice(priceFindRequest);
    }

}
