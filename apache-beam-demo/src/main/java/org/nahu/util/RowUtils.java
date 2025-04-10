package org.nahu.util;

import org.nahu.dto.TickerDTO;
import org.nahu.dto.TickerResultsDTO;
import org.nahu.exceptions.InvalidParameterException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.nahu.constants.CsvConstants.BIG_DECIMAL_SCALE;
import static org.nahu.constants.CsvConstants.CSV_DELIMITER;

public class RowUtils {

    public static String buildRow(TickerDTO ticker, TickerResultsDTO tickerResult){

        if(ticker == null || tickerResult == null){
            throw new InvalidParameterException("buildRow():: parameters cannot be null");
        }

        StringBuilder csvRowBuilder = new StringBuilder();
        return csvRowBuilder
                .append(ticker.getRequestId()).append(CSV_DELIMITER)
                .append(ticker.getTicker()).append(CSV_DELIMITER)
                .append(truncate(tickerResult.getO())).append(CSV_DELIMITER)
                .append(truncate(tickerResult.getC())).append(CSV_DELIMITER)
                .append(truncate(tickerResult.getH())).append(CSV_DELIMITER)
                .append(truncate(tickerResult.getL())).append(CSV_DELIMITER)
                .append(truncate(tickerResult.getV()))
                .toString();
    }

    public static BigDecimal truncate(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return BigDecimal.ZERO;
        }
        return bigDecimal.setScale(BIG_DECIMAL_SCALE, RoundingMode.DOWN);
    }


}
