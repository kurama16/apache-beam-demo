package org.nahu.client;

import org.junit.Test;
import org.nahu.client.impl.DummyStockClient;
import org.nahu.client.impl.PolygonStockClient;
import org.nahu.enums.ApiClientsEnum;

import static org.junit.Assert.assertEquals;


public class StockClientTest {

    @Test
    public void should_return_dummyClient_when_parameter_is_Dummy() {

        StockClient stockClient = StockClient.getClient(ApiClientsEnum.DUMMY_CLIENT);
        Class<DummyStockClient> expectedValue = DummyStockClient.class;
        assertEquals(stockClient.getClass(), expectedValue);

    }

    @Test
    public void should_return_PolygonClient_when_parameter_is_Polygon() {

        StockClient stockClient = StockClient.getClient(ApiClientsEnum.POLYGON_CLIENT);
        Class<PolygonStockClient> expectedValue = PolygonStockClient.class;
        assertEquals(stockClient.getClass(), expectedValue);

    }

}