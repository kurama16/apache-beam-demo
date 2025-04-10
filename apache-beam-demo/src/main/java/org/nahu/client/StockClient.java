package org.nahu.client;

import org.nahu.client.impl.DummyStockClient;
import org.nahu.client.impl.PolygonStockClient;
import org.nahu.client.request.RequestParams;
import org.nahu.enums.ApiClientsEnum;

public interface StockClient {


    String apiCall(String url, RequestParams requestParams);
    static StockClient getClient(ApiClientsEnum client) {

        switch (client){
            case DUMMY_CLIENT -> {
                return new DummyStockClient();
            }
            case POLYGON_CLIENT -> {
                return new PolygonStockClient();
            }
            default -> {
                return null;
            }

        }
    }
}
