package com.progresssoft.entity;

import com.progresssoft.model.FxDeal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fx_deals")
public class FxDealEntity {

    @Id
    private Long id;
    private String fromISOCurrency;
    private String toISOCurrency;
    private LocalDateTime dealTimestamp;
    private BigDecimal amount;

    public FxDealEntity convertToEntity(FxDeal fxDeal) {
        return FxDealEntity.builder()
                .id(Long.parseLong(fxDeal.getId()))
                .fromISOCurrency(fxDeal.getFromISOCurrency())
                .toISOCurrency(fxDeal.getToISOCurrency())
                .dealTimestamp(LocalDateTime.parse(fxDeal.getDealTimestamp()))
                .amount(fxDeal.getAmount())
                .build();
    }
}