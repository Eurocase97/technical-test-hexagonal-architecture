package com.prueba.tecnica.application.usecase.service;

import com.prueba.tecnica.domain.exception.PriceNotFoundException;
import com.prueba.tecnica.domain.model.Price;
import com.prueba.tecnica.domain.ports.in.GetApplicablePriceUseCase;
import com.prueba.tecnica.domain.ports.out.PriceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Transactional
public class GetApplicablePriceService implements GetApplicablePriceUseCase {


    private final PriceRepository priceRepository;

    public GetApplicablePriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepository
                .getApplicablePrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No applicable price found for product %d, brand %d at %s",
                                productId, brandId, applicationDate)
                ));
    }
}
