package org.nahu.client.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestParams {
    private String stockTicker;
    private String fromDate;
    private String toDate;
    private String apiKey;
}