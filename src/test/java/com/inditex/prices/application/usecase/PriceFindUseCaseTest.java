package com.inditex.prices.application.usecase;

import com.inditex.prices.application.usecase.price.PriceFindUseCase;
import com.inditex.prices.domain.aggreate.PriceAggregate;
import com.inditex.prices.domain.repository.IPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class PriceFindUseCaseTest {

    @Autowired
    PriceFindUseCase priceFindUseCase;

    @MockBean
    IPriceRepository priceRepository;

    @Test
    void when_valid_price() {
        // when
        PriceAggregate result = priceFindUseCase.execute(Mockito.any());
        when(priceRepository.findPrice(Mockito.any())).thenReturn(PriceAggregate.builder().build());

        // then
        assertTrue(Objects.isNull(result));
        verify(priceRepository, times(1)).findPrice(Mockito.any());
    }

    @Test
    void when_invalid_price() {
        // when
        PriceAggregate result = priceFindUseCase.execute(Mockito.any());
        when(priceRepository.findPrice(Mockito.any())).thenReturn(null);

        // then
        assertTrue(Objects.isNull(result));
        verify(priceRepository, times(1)).findPrice(Mockito.any());
    }

}
