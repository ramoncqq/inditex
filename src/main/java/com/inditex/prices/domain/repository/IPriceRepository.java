package com.inditex.prices.domain.repository;

import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.infrastructure.input.rest.price.findprice.request.PriceFindRequest;
import com.inditex.prices.domain.exception.NotContentException;

public interface IPriceRepository {

    PriceAggregate findPrice(PriceFindRequest priceFindRequest) throws NotContentException;

}
