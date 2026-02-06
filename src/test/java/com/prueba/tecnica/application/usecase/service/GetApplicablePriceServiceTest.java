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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private Price expectedPrice;

    @BeforeEach
    void setUp() {
        productId = 35455L;
        brandId = 1L;
        applicationDate = LocalDateTime.of(2024, 6, 14, 10, 0);

        expectedPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1L)
                .startDate(LocalDateTime.of(2024, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 23, 59, 59))
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();
    }

    @Test
    void shouldReturnPriceWhenApplicablePriceExists() {
        when(priceRepository.getApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(expectedPrice));

        Price result = service.execute(productId, brandId, applicationDate);

        assertNotNull(result);
        assertEquals(expectedPrice.getProductId(), result.getProductId());
        assertEquals(expectedPrice.getBrandId(), result.getBrandId());
        assertEquals(expectedPrice.getPriceList(), result.getPriceList());
        assertEquals(expectedPrice.getPrice(), result.getPrice());
        assertEquals(expectedPrice.getCurrency(), result.getCurrency());

    }

    @Test
    void shouldThrowPriceNotFoundException() {
        when(priceRepository.getApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(
                PriceNotFoundException.class,
                () -> service.execute(productId, brandId, applicationDate)
        );
        assertTrue(exception.getMessage().contains("No applicable price found"));
    }
}
