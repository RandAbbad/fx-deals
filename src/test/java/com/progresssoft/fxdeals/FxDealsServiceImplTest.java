package com.progresssoft.fxdeals;

import com.progresssoft.entity.FxDealEntity;
import com.progresssoft.model.FxDeal;
import com.progresssoft.model.FxDealResponse;
import com.progresssoft.repository.FxDealsRepository;
import com.progresssoft.service.FxDealsService;
import com.progresssoft.service.FxDealsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class FxDealsServiceImplTest {

    private FxDealsRepository fxDealsRepository;
    private FxDealsService fxDealsService;
    private final FxDeal fxDeal = new FxDeal("1", "JOD", "USD", "2023-11-01T03:05:11", BigDecimal.TEN);
    private final FxDealEntity entity = new FxDealEntity(1L, "JOD", "USD", LocalDateTime.parse("2023-11-01T03:05:11"), BigDecimal.TEN);

    @BeforeEach
    public void setup() {
        fxDealsRepository = mock(FxDealsRepository.class);
        fxDealsService = new FxDealsServiceImpl(fxDealsRepository);
    }

    @Test
    void givenValidFxDeal_whenAddFxDeal_shouldReturnCreatedResponse() {
        when(fxDealsRepository.findById(entity.getId())).thenReturn(Optional.empty());
        FxDealResponse response = fxDealsService.addFxDeal(fxDeal);

        verify(fxDealsRepository).save(entity);
        assertEquals(CREATED.value(), response.getStatusCode());
    }

    @Test
    void givenInvalidFxDeal_whenAddFxDeal_shouldReturnBadRequestResponse() {
        FxDeal invalidFxDeal = new FxDeal("1", "X", "USD", "2023-10-01T03:05:11", BigDecimal.TEN);
        when(fxDealsRepository.findById(entity.getId())).thenReturn(Optional.empty());
        FxDealResponse response = fxDealsService.addFxDeal(invalidFxDeal);

        verify(fxDealsRepository, never()).save(entity);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode());
    }


    @Test
    void givenDuplicateFxDeal_whenAddFxDeal_shouldReturnBadRequestResponse() {
        when(fxDealsRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        FxDealResponse response = fxDealsService.addFxDeal(fxDeal);

        verify(fxDealsRepository, never()).save(entity);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    void givenValidId_whenGetFxDealById_shouldReturnOkResponse() {
        long id = entity.getId();
        when(fxDealsRepository.findById(id)).thenReturn(Optional.of(entity));
        FxDealResponse response = fxDealsService.getFxDealById(id);

        verify(fxDealsRepository).findById(id);
        assertEquals(OK.value(), response.getStatusCode());
    }

    @Test
    void givenInvalidId_whenGetFxDealById_shouldReturnNoContentResponse() {
        Long id = 0L;
        when(fxDealsRepository.findById(id)).thenReturn(Optional.empty());
        FxDealResponse response = fxDealsService.getFxDealById(id);

        assertEquals(NO_CONTENT.value(), response.getStatusCode());
    }

    @Test
    void givenNonEmptyTable_whenGetAllFxDeals_shouldReturnOkResponse() {
        when(fxDealsRepository.findAll()).thenReturn(List.of(entity));
        FxDealResponse response = fxDealsService.getAllFxDeals();

        assertEquals(OK.value(), response.getStatusCode());
    }

    @Test
    void givenEmptyTable_whenGetAllFxDeals_shouldReturnNoContentResponse() {
        when(fxDealsRepository.findAll()).thenReturn(List.of());
        FxDealResponse response = fxDealsService.getAllFxDeals();

        assertEquals(NO_CONTENT.value(), response.getStatusCode());
    }
}