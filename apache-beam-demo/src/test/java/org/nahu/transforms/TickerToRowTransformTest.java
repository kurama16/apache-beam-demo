package org.nahu.transforms;

import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.nahu.dto.TickerDTO;

import static org.nahu.testutils.TestUtils.getSimpleTickerDTO;

public class TickerToRowTransformTest {

    @Rule
    public final transient TestPipeline pipeline = TestPipeline.create();


    @Test
    public void should_return_PCollectionString_when_isCall_successfully() {

        PCollection<TickerDTO> stringCollection = pipeline.apply("create String Collection", Create.of(getSimpleTickerDTO()));

        PCollection<String> csvRowCollection = stringCollection.apply("test transform", new TickerToRowTransform());

        String expectedValue = "123,TEST,1.00,1.00,1.00,1.00,1.00";

        PAssert.that(csvRowCollection).containsInAnyOrder(expectedValue);

        pipeline.run().waitUntilFinish();
    }
}