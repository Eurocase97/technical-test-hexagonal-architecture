package com.prueba.tecnica.Infrastructure.adapter.in.controller.mapper;

import com.prueba.tecnica.Infrastructure.adapter.in.controller.dto.PriceResponse;
import com.prueba.tecnica.Infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.prueba.tecnica.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceResponse toResponse(Price domain);
    Price toDomain(PriceEntity priceEntity);
}
