package org.nahu.client.impl;

import org.junit.Test;
import org.nahu.client.request.RequestParams;

import static org.junit.Assert.assertEquals;

public class DummyStockClientTest {

    @Test
    public void should_Return_String_When_call() {
        DummyStockClient client = new DummyStockClient();

        String url = "dummy-url";
        RequestParams request = RequestParams.builder().build();
        String expectedValue = getDummyResponse();

        String response = client.apiCall(url, request);

        assertEquals(expectedValue, response);

    }

    private String getDummyResponse() {
        return """
                {
                  "adjusted": true,
                  "next_url": "https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/1578114000000/2020-01-10?cursor=bGltaXQ9MiZzb3J0PWFzYw",
                  "queryCount": 2,
                  "request_id": "6a7e466379af0a71039d60cc78e72282",
                  "results": [
                    {
                      "c": 75.0875,
                      "h": 75.15,
                      "l": 73.7975,
                      "n": 1,
                      "o": 74.06,
                      "t": 1577941200000,
                      "v": 135647456,
                      "vw": 74.6099
                    },
                    {
                      "c": 74.3575,
                      "h": 75.145,
                      "l": 74.125,
                      "n": 1,
                      "o": 74.2875,
                      "t": 1578027600000,
                      "v": 146535512,
                      "vw": 74.7026
                    }
                  ],
                  "resultsCount": 2,
                  "status": "OK",
                  "ticker": "AAPL"
                }
                """;
    }
}