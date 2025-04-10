package org.nahu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickerResultsDTO implements Serializable {

    private BigDecimal c;
    private BigDecimal h;
    private BigDecimal l;
    private BigDecimal o;
    private BigDecimal v;
}
