package com.prueba.tecnica.Infrastructure.adapter.out.persistence.repository;

import com.prueba.tecnica.Infrastructure.adapter.in.controller.mapper.PriceMapper;
import com.prueba.tecnica.Infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.prueba.tecnica.domain.model.Price;
import com.prueba.tecnica.domain.ports.out.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PriceRepositoryAdapter implements PriceRepository {
    
    private final JPARepository jpaRepository;
    private final PriceMapper mapper;

    public PriceRepositoryAdapter(JPARepository jpaRepository, PriceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public  List<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<PriceEntity> priceResult =  jpaRepository.findApplicablePrice(productId, brandId, applicationDate);
        return priceResult.stream().map(mapper::toDomain).toList();
    }
}
