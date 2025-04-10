package org.nahu.util;

import org.junit.Test;
import org.nahu.dto.TickerDTO;
import org.nahu.dto.TickerResultsDTO;
import org.nahu.exceptions.InvalidParameterException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class RowUtilsTest {

    @Test
    public void should_ReturnRow_When_args_notNull() {
        TickerDTO ticker = new TickerDTO();
        TickerResultsDTO tickerResults = new TickerResultsDTO();
        String expectedValue = "null,null,0,0,0,0,0";

        String response = RowUtils.buildRow(ticker, tickerResults);

        assertEquals(expectedValue, response);

    }


    @Test(expected = InvalidParameterException.class )
    public void should_throw_InvalidParameterException_When_args_null() {
        TickerDTO ticker = null;
        TickerResultsDTO tickerResults = null;

        RowUtils.buildRow(ticker, tickerResults);

    }

    @Test
    public void should_ReturnRoundedBigDecimal_When_BigDecimal_isValid() {
        BigDecimal bigDecimal = new BigDecimal("2.323");
        BigDecimal expectedValue = new BigDecimal("2.32");

        BigDecimal response = RowUtils.truncate(bigDecimal);

        assertEquals(expectedValue, response);
    }


    @Test
    public void should_ReturnZero_When_BigDecimal_isNull() {
        BigDecimal bigDecimal = null;
        BigDecimal expectedValue = BigDecimal.ZERO;

        BigDecimal response = RowUtils.truncate(bigDecimal);

        assertEquals(expectedValue, response);
    }
}