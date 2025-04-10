package org.nahu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickerDTO implements Serializable {
    private boolean adjusted;
    @JsonProperty("next_url")
    private String nextUrl;
    private String queryCount;
    @JsonProperty("request_id")
    private String requestId;
    private String ticker;
    private List<TickerResultsDTO> results;
}
