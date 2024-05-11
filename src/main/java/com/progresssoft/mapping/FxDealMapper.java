package com.progresssoft.mapping;

import com.progresssoft.entity.FxDealEntity;
import com.progresssoft.model.FxDeal;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class FxDealMapper {
    public static FxDealEntity convertToEntity(FxDeal fxDeal) {
        return FxDealEntity.builder()
                .id(Long.parseLong(fxDeal.getId()))
                .fromISOCurrency(fxDeal.getFromISOCurrency())
                .toISOCurrency(fxDeal.getToISOCurrency())
                .dealTimestamp(LocalDateTime.parse(fxDeal.getDealTimestamp()))
                .amount(fxDeal.getAmount())
                .build();

    }
}
