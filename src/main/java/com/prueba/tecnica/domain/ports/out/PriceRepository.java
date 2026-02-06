package com.prueba.tecnica.domain.ports.out;

import com.prueba.tecnica.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) ;
}
