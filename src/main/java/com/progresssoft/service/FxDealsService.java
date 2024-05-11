package com.progresssoft.service;

import com.progresssoft.model.FxDeal;
import com.progresssoft.model.FxDealResponse;


public interface FxDealsService {

    /**
     * Adds a new FX deal to the system.
     *
     * @param fxDeal The FX deal to be added.
     * @return A response containing information about the result of the operation.
     */
    FxDealResponse addFxDeal(FxDeal fxDeal);

    /**
     * Retrieves an FX deal by its unique identifier.
     *
     * @param id The unique identifier of the FX deal.
     * @return A response containing the FX deal information if found, or an error message if not found.
     */
    FxDealResponse getFxDealById(Long id);

    /**
     * Retrieves all FX deals stored in the system.
     *
     * @return A response containing a list of all FX deals, or an error message if no deals are found.
     */
    FxDealResponse getAllFxDeals();

}