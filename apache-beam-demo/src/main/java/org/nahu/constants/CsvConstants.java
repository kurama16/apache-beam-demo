package org.nahu.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvConstants {

    public static final String CSV_HEADER = "id, company_symbol, open_price, close_price, high_price, low_price, volume";
    public static final String CSV_SUFFIX = ".csv";
    public static final String CSV_DELIMITER = ",";

    public static final int BIG_DECIMAL_SCALE = 2;
}
