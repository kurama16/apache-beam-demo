package org.nahu.client.impl;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.nahu.client.APIClient;
import org.nahu.client.request.RequestParams;
import org.nahu.exceptions.HttpClientException;
import org.nahu.exceptions.HttpClientNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PolygonStockClientTest {

    @Mock
    OkHttpClient okHttpClient;

    @Mock
    Call call;

    @Mock
    Response response;

    @Mock
    ResponseBody body;

    @Test
    public void should_RetString_When_call_successfully() throws IOException {
        PolygonStockClient client = new PolygonStockClient();

        try (MockedStatic<APIClient> apiClientMock = Mockito.mockStatic(APIClient.class)) {
            String expectedValue = "DummyResponse";

            when(body.string()).thenReturn(expectedValue);

            when(response.body()).thenReturn(body);
            when(response.isSuccessful()).thenReturn(Boolean.TRUE);

            when(call.execute()).thenReturn(response);

            when(okHttpClient.newCall(any())).thenReturn(call);

            apiClientMock.when(APIClient::getClient).thenReturn(okHttpClient);

            String url = "https://api.polygon.io/v2";
            RequestParams request = getSimpleRequestParam();

            String response = client.apiCall(url, request);

            assertEquals(expectedValue, response);

        }

    }


    @Test(expected = HttpClientNotFoundException.class)
    public void should_Throw_HttpClientNotFoundException_When_call_unsuccessfully() throws IOException {
        PolygonStockClient client = new PolygonStockClient();

        try (MockedStatic<APIClient> apiClientMock = Mockito.mockStatic(APIClient.class)) {

            when(response.isSuccessful()).thenReturn(Boolean.FALSE);
            when(call.execute()).thenReturn(response);
            when(okHttpClient.newCall(any())).thenReturn(call);
            apiClientMock.when(APIClient::getClient).thenReturn(okHttpClient);

            String url = "https://api.polygon.io/v2";
            RequestParams request = getSimpleRequestParam();

            client.apiCall(url, request);

        }

    }

    @Test(expected = HttpClientException.class)
    public void should_Throw_HttpClientNotFoundException_When_call_haveAnError() throws IOException {
        PolygonStockClient client = new PolygonStockClient();

        try (MockedStatic<APIClient> apiClientMock = Mockito.mockStatic(APIClient.class)) {

            when(call.execute()).thenThrow(new IOException());
            when(okHttpClient.newCall(any())).thenReturn(call);
            apiClientMock.when(APIClient::getClient).thenReturn(okHttpClient);

            String url = "https://api.polygon.io/v2";
            RequestParams request = getSimpleRequestParam();

            client.apiCall(url, request);

        }

    }

    private RequestParams getSimpleRequestParam() {
        return RequestParams.builder()
                .stockTicker("AAPL")
                .fromDate("2025-04-05")
                .toDate("2025-04-10")
                .apiKey("5uhhYyp2c2PwBIBK3mOwde14wTtb4cFQ").build();
    }
}