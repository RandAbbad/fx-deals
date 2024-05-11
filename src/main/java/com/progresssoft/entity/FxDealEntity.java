package com.progresssoft.entity;

import com.progresssoft.model.FxDeal;
import jakarta.persistence.Column;
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
    @Column(name="id")
    private Long id;

    @Column(name="fromISOCurrency")
    private String fromISOCurrency;

    @Column(name="toISOCurrency")
    private String toISOCurrency;

    @Column(name="dealTimestamp")
    private LocalDateTime dealTimestamp;

    @Column(name="amount")
    private BigDecimal amount;

}