package com.prueba.tecnica.application.usecase.service;

import com.prueba.tecnica.domain.exception.PriceNotFoundException;
import com.prueba.tecnica.domain.model.Price;
import com.prueba.tecnica.domain.ports.out.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetApplicablePriceService service;

    private Long productId;
    private Long brandId;
    private LocalDateTime applicationDate;

    @BeforeEach
    void setUp() {
        productId = 35455L;
        brandId = 1L;
        applicationDate = LocalDateTime.of(2024, 6, 14, 10, 0);
    }

    @Test
    void ShouldReturnPriceWithHighestPriority() {
        Price lowPriorityPrice = createPrice(1L, 0, new BigDecimal("35.50"));
        Price highPriorityPrice = createPrice(2L, 1, new BigDecimal("25.45"));

        when(priceRepository.getApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(List.of(lowPriorityPrice, highPriorityPrice));

        Price result = service.execute(productId, brandId, applicationDate);

        assertThat(result).isEqualTo(highPriorityPrice);
        assertThat(result.getPriority()).isEqualTo(1);
    }

    @Test
    void shouldThrowPriceNotFoundException() {
        when(priceRepository.getApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> service.execute(productId, brandId, applicationDate))
                .isInstanceOf(PriceNotFoundException.class);
    }

    private Price createPrice(Long id, Integer priority, BigDecimal priceValue) {
        return Price.builder()
                .id(id)
                .brandId(brandId)
                .productId(productId)
                .priority(priority)
                .price(priceValue)
                .currency("EUR")
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .build();
    }
}
