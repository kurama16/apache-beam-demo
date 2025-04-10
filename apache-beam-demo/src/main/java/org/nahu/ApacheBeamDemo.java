package org.nahu;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.nahu.client.StockClient;
import org.nahu.client.request.RequestParams;
import org.nahu.enums.ApiClientsEnum;
import org.nahu.options.ApacheDemoOptions;
import org.nahu.transforms.StringToTickerTransform;
import org.nahu.transforms.TickerToRowTransform;

import static org.nahu.constants.CsvConstants.CSV_HEADER;
import static org.nahu.constants.CsvConstants.CSV_SUFFIX;

public class ApacheBeamDemo {

    public static void main(String[] args) {

        ApacheDemoOptions options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(ApacheDemoOptions.class);

        Pipeline pipeline = Pipeline.create(options);

        preparePipeline(pipeline, options);

        pipeline.run().waitUntilFinish();

    }
    public static void preparePipeline(Pipeline pipeline, ApacheDemoOptions options){
        StockClient client = StockClient.getClient(ApiClientsEnum.valueOf(options.getApiClient()));

        RequestParams requestParams = RequestParams.builder()
                .stockTicker(options.getStockTicker())
                .fromDate(options.getFromDate())
                .toDate(options.getToDate())
                .apiKey(options.getApiKey())
                .build();

        String apiResponse = client.apiCall(options.getUrl(), requestParams);

        pipeline.apply("Create PCollection from API response", Create.of(apiResponse))
                .apply("String to TickerDTO", new StringToTickerTransform())
                .apply("TickerDTO to row", new TickerToRowTransform())
                .apply("Write json file", TextIO.write()
                        .to(options.getOutputPath())
                        .withSuffix(CSV_SUFFIX)
                        .withHeader(CSV_HEADER)
                        .withoutSharding());
    }


}