package com.progresssoft.service;

import com.progresssoft.entity.FxDealEntity;
import com.progresssoft.exceptions.*;
import com.progresssoft.model.FxDeal;
import com.progresssoft.model.FxDealResponse;
import com.progresssoft.repository.FxDealsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FxDealsServiceImpl implements FxDealsService {

    private final FxDealsRepository fxDealRepository;

    @Override
    @Transactional
    public FxDealResponse addFxDeal(FxDeal fxDeal) {
        try {
            log.info("Adding FX Deal with ID: {}", fxDeal.getId());
            validateFxDeal(fxDeal);
            FxDealEntity entity = new FxDealEntity().convertToEntity(fxDeal);

            fxDealRepository.findById(entity.getId())
                    .ifPresent(e -> {
                        throw new DuplicateDealException("FX Deal with ID already exists: %s".formatted(entity.getId()));
                    });
            FxDealEntity savedDeal = fxDealRepository.save(entity);
            String message = "Deal with ID %s successfully saved".formatted(entity.getId());
            log.info(message);
            return new FxDealResponse(CREATED.value(), message, savedDeal);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new FxDealResponse(BAD_REQUEST.value(), e.getMessage(), null);
        }
    }

    @Override
    public FxDealResponse getFxDealById(Long id) {
        try {
            log.info("Fetching FX deal with ID: {}", id);
            FxDealEntity entity = fxDealRepository.findById(id)
                    .orElseThrow(() -> new DealNotFoundException("Deal with ID %s not found".formatted(id)));
            String message = "Deal with id %s is retrieved successfully".formatted(id);
            log.info(message);
            return new FxDealResponse(OK.value(), message, entity);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new FxDealResponse(NO_CONTENT.value(), e.getMessage(), null);
        }
    }

    @Override
    public FxDealResponse getAllFxDeals() {
        try {
            log.info("Fetching all FX deals");
            List<FxDealEntity> entities = fxDealRepository.findAll();
            if (entities.isEmpty()) {
                throw new NoDealsFoundException("No deals were found");
            }
            String message = "Deals were retrieved successfully";
            log.info(message);
            return new FxDealResponse(OK.value(), message, entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new FxDealResponse(NO_CONTENT.value(), e.getMessage(), null);
        }
    }

    private void validateFxDeal(FxDeal fxDeal) {
        if (!isValidCurrency(fxDeal.getFromISOCurrency())) {
            throw new InvalidCurrencyException("Invalid from ISO currency code: %s".formatted(fxDeal.getFromISOCurrency()));
        }
        if (!isValidCurrency(fxDeal.getToISOCurrency())) {
            throw new InvalidCurrencyException("Invalid to ISO currency code: %s".formatted(fxDeal.getToISOCurrency()));
        }
        if (fxDeal.getFromISOCurrency().equals(fxDeal.getToISOCurrency())) {
            throw new InvalidCurrencyException("to and from ISO currency codes can't be the same: %s".formatted(fxDeal.getToISOCurrency()));
        }
        if (fxDeal.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero: %s".formatted(fxDeal.getAmount()));
        }
    }

    private boolean isValidCurrency(String currency) {
        try {
            Currency.getInstance(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}