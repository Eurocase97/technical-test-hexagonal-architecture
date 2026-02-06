package com.prueba.tecnica.Infrastructure.adapter.out.persistence.repository;

import com.prueba.tecnica.Infrastructure.adapter.out.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JPARepository  extends JpaRepository<PriceEntity, Long> {

    @Query(
            value = """
        SELECT *
        FROM price p
        WHERE p.product_id = :productId
          AND p.brand_id = :brandId
          AND p.start_date <= :applicationDate
          AND p.end_date   >= :applicationDate
    """,
            nativeQuery = true
    )
    List<PriceEntity> findApplicablePrice(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}
