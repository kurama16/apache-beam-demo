package org.nahu.client.impl;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.nahu.client.APIClient;
import org.nahu.client.StockClient;
import org.nahu.client.request.RequestParams;
import org.nahu.exceptions.HttpClientException;
import org.nahu.exceptions.HttpClientNotFoundException;

import java.io.IOException;

public class PolygonStockClient implements StockClient {


    @Override
    public String apiCall(String url, RequestParams requestParams) {

        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addPathSegment("aggs")
                .addPathSegment("ticker")
                .addPathSegment(requestParams.getStockTicker())
                .addPathSegment("range")
                .addPathSegment("1")
                .addPathSegment("day")
                .addPathSegment(requestParams.getFromDate())
                .addPathSegment(requestParams.getToDate())
                .addQueryParameter("limit","120")
                .addQueryParameter("apiKey",requestParams.getApiKey())
                .build();

        Request request = new Request.Builder()
                .url(httpUrl.url())
                .build();

         try(Response response = APIClient.getClient().newCall(request).execute()){
             if(response.isSuccessful()){
                 return response.body().string();
             }

             throw new HttpClientNotFoundException("error querying the results");

         } catch (IOException e) {
            throw new HttpClientException("error calling Polygon API: "+ e.getMessage());
        }

    }
}
