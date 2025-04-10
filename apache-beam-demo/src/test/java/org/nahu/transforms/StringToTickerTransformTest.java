package org.nahu.transforms;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.nahu.dto.TickerDTO;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.nahu.testutils.TestUtils.getSimpleJsonString;


public class StringToTickerTransformTest {

    @Rule
    public final transient TestPipeline pipeline = TestPipeline.create();

    @Test
    public void should_return_PCollectionTicketDTO_when_isCall_successfully() {
        PCollection<String> stringCollection = pipeline.apply("create String Collection", Create.of(getSimpleJsonString()));

        PCollection<TickerDTO> tickerDTOCollection = stringCollection.apply("test transform", new StringToTickerTransform());
        long expected = 1;

        PAssert.that(tickerDTOCollection).satisfies(p -> {
            Stream<TickerDTO> stream = StreamSupport.stream(p.spliterator(), false);

            long count = stream.count();

            assertEquals(expected, count);
            return null;
        });
        pipeline.run().waitUntilFinish();
    }


    @Test(expected = Pipeline.PipelineExecutionException.class)
    public void should_throw_jsonParserException_when_json_isIncorrect() {
        PCollection<String> stringCollection = pipeline.apply("create String Collection", Create.of("incorrect json"));

        PCollection<TickerDTO> tickerDTOCollection = stringCollection.apply("test transform", new StringToTickerTransform());

        pipeline.run().waitUntilFinish();


    }


}