package com.progresssoft.model;

import lombok.*;
@Getter
@AllArgsConstructor
public class FxDealResponse {
    private int statusCode;
    private String message;
    private Object data;
}
