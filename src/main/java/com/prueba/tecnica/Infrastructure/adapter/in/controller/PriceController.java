package com.prueba.tecnica.Infrastructure.adapter.in.controller;

import com.prueba.tecnica.Infrastructure.adapter.in.controller.mapper.PriceMapperImpl;
import com.prueba.tecnica.domain.model.Price;
import com.prueba.tecnica.domain.ports.in.GetApplicablePriceUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;
    private final PriceMapperImpl priceMapper;


    public PriceController(GetApplicablePriceUseCase getApplicablePriceUseCase, PriceMapperImpl priceMapper) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
        this.priceMapper = priceMapper;
    }

    @GetMapping
    public ResponseEntity<?> getApplicablePrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
            Price price = getApplicablePriceUseCase.execute(productId, brandId, applicationDate);
            return ResponseEntity.ok(priceMapper.toResponse(price));
    }
}