package com.inditex.prices.infraestructure.input.rest.price;

import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.infrastructure.input.rest.shared.ApiDto;
import com.inditex.prices.infrastructure.input.rest.price.findprice.request.PriceFindRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

    private final TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    @Autowired
    public PriceControllerTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    private static Stream<Arguments> daysHours() {
        return Stream.of(
                arguments(14, 10, Boolean.TRUE),
                arguments(14, 16, Boolean.FALSE),
                arguments(14, 21, Boolean.FALSE),
                arguments(15, 10, Boolean.TRUE),
                arguments(16, 21, Boolean.FALSE)
        );

    }

    @ParameterizedTest(name = "{index} => day={0}, hour={1}, existData={2}")
    @MethodSource(value = "daysHours")
    void findPrice(int day, int month, Boolean existData) {
        HttpEntity<PriceFindRequest> body = new HttpEntity<>(buildPriceFindDTO(day, month));
        final String url = "http://localhost:" + port + "/price";

        ApiDto<PriceAggregate> priceAggregate = testRestTemplate.exchange(url, HttpMethod.POST, body,
                new ParameterizedTypeReference<ApiDto<PriceAggregate>>() {
                }).getBody();

        assertThat(existData).isNotNull();
        assertSame(existData, priceAggregate != null);
    }

    private PriceFindRequest buildPriceFindDTO(int day, int hour) {
        int year = 2020;
        int month = 6;
        return PriceFindRequest.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.of(year, month, day, hour, 0))
                .build();
    }

}
