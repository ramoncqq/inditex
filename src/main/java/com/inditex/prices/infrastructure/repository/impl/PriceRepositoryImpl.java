package com.inditex.prices.infrastructure.repository.impl;

import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.infrastructure.input.rest.price.findprice.request.PriceFindRequest;
import com.inditex.prices.domain.exception.NotContentException;
import com.inditex.prices.infrastructure.output.db.entity.PriceModel;
import com.inditex.prices.domain.repository.IPriceRepository;
import com.inditex.prices.infrastructure.output.db.repository.PriceJpaRepository;
import com.inditex.prices.infrastructure.output.db.shared.FilterOperator;
import com.inditex.prices.infrastructure.output.db.shared.QuerySpecificationsBuilder;
import com.inditex.prices.infrastructure.output.db.shared.SortBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceRepositoryImpl implements IPriceRepository {

    private final PriceJpaRepository repository;

    @Override
    public PriceAggregate findPrice(PriceFindRequest priceFindRequest) throws NotContentException {
        // Esto lo he hecho así aunque quizás lo más lógico es que el endDate viniera informado
        final LocalDateTime endDate = LocalTime.MAX.atDate(priceFindRequest.getStartDate().toLocalDate());

        QuerySpecificationsBuilder<PriceModel> builder = new QuerySpecificationsBuilder<>();
        builder.with("productId", FilterOperator.EQUAL, priceFindRequest.getProductId());
        builder.with("brandId", FilterOperator.EQUAL, priceFindRequest.getBrandId());
        builder.with("startDate", FilterOperator.GREATER_THAN_OR_EQUAL, priceFindRequest.getStartDate());
        builder.with("endDate", FilterOperator.LOWER_THAN_OR_EQUAL, endDate);

        log.info("find prices params: {}", priceFindRequest);

        List<PriceModel> result = repository.findAll(
                builder.buildSpecifications(FilterOperator.AND),
                SortBuilder.buildSort(Sort.Direction.DESC, "priority")
        );

        return result.stream().findFirst().map(this::convertToModel).orElseThrow(NotContentException::new);
    }


    private PriceAggregate convertToModel(PriceModel priceModel) {
        return PriceAggregate.builder()
                .price(Price.builder()
                        .productId(priceModel.getProductId())
                        .price(priceModel.getPrice())
                        .priceList(priceModel.getPriceList())
                        .startDate(priceModel.getStartDate())
                        .endDate(priceModel.getEndDate())
                        .build())
                .brand(Brand.builder().id(priceModel.getBrandId()).build())
                .build();
    }

}
