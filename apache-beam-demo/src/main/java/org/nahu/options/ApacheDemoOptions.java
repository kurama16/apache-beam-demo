package org.nahu.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

public interface ApacheDemoOptions extends PipelineOptions {
    @Description("output path parameter for the csv file")
    @Default.String("src/main/resources/out")
    String getOutputPath();
    void setOutputPath(String value);

    @Description("url")
    @Validation.Required
    String getUrl();
    void setUrl(String value);

    @Description("name of the API to use. to see available options check ApiClientsEnum ")
    @Validation.Required
    String getApiClient();
    void setApiClient(String value);

    @Description("ticker symbol to search")
    @Default.String("AAPL")
    String getStockTicker();
    void setStockTicker(String value);

    @Description("start of the aggregate  window. should be in format YYYY-MM-DD")
    @Default.String("2025-01-09")
    String getFromDate();
    void setFromDate(String value);

    @Description("end of the aggregate  window. should be in format YYYY-MM-DD")
    @Default.String("2025-02-10")
    String getToDate();
    void setToDate(String value);


    @Description("Apikey used to authenticate")
    @Validation.Required
    String getApiKey();
    void setApiKey(String value);


}
