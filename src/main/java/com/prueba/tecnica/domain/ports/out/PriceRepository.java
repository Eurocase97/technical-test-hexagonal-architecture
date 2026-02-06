package com.prueba.tecnica.domain.ports.out;

import com.prueba.tecnica.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {
    List<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) ;
}
