package com.progresssoft.controller;

import com.progresssoft.model.FxDeal;
import com.progresssoft.model.FxDealResponse;
import com.progresssoft.service.FxDealsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deals")
@RequiredArgsConstructor
public class FxDealsController {
    @Autowired
    private final FxDealsService fxDealsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<FxDealResponse> getFxDealById(@PathVariable Long id){
        FxDealResponse response = fxDealsService.getFxDealById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<FxDealResponse> getAllFxDeals(){
        FxDealResponse response = fxDealsService.getAllFxDeals();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<FxDealResponse> addFxDeal(@Valid @RequestBody FxDeal fxDeal){
        FxDealResponse response = fxDealsService.addFxDeal(fxDeal);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
