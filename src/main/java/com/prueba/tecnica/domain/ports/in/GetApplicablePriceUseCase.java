package com.prueba.tecnica.domain.ports.in;

import com.prueba.tecnica.domain.model.Price;

import java.time.LocalDateTime;


public interface GetApplicablePriceUseCase {
    Price execute(Long productId, Long brandId, LocalDateTime applicationDate) ;
}
