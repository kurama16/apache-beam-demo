package org.nahu;

import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.testing.TestPipeline;
import org.junit.Rule;
import org.junit.Test;
import org.nahu.options.ApacheDemoOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ApacheBeamDemoTest {

    @Rule
    public final transient TestPipeline pipeline = TestPipeline.create();


    @Test
    public void should_create_csvFile_when_runs_successfully() throws IOException {

        String[] args = {
                "--outputPath=src/main/resources/out",
                "--apiClient=DUMMY_CLIENT",
                "--url=https://api.polygon.io/v2",
                "--stockTicker=AAPL",
                "--fromDate=2025-01-09",
                "--toDate=2025-01-10",
                "--apiKey=5uhhYyp2c2PwBIBK3mOwde14wTtb4cFQ"
        };

        ApacheDemoOptions options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(ApacheDemoOptions.class);

        ApacheBeamDemo.preparePipeline(pipeline,options);

        pipeline.run().waitUntilFinish();

        Path expectedFileLocation = Paths.get(options.getOutputPath()+".csv");

        assertTrue(Files.exists(expectedFileLocation));

        Files.delete(expectedFileLocation);

        assertTrue(Files.notExists(expectedFileLocation));

    }
}