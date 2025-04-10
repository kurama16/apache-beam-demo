package org.nahu.transforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.nahu.dto.TickerDTO;
import org.nahu.exceptions.JsonParserException;

public class StringToTickerTransform extends PTransform<PCollection<String>, PCollection<TickerDTO>> {
    @Override
    public PCollection<TickerDTO> expand(PCollection<String> input) {

        return input.apply("transform String to json", ParDo.of(new DoFn<String, TickerDTO>() {
            private static final ObjectMapper objectMapper = new ObjectMapper();

            @ProcessElement
            public void processElement(@Element String json, OutputReceiver<TickerDTO> out) {
                try {
                    TickerDTO tickerDTO = objectMapper.readValue(json, TickerDTO.class);
                    out.output(tickerDTO);
                } catch (JsonProcessingException e) {
                    throw new JsonParserException("Error parsing the exception: "+ e.getMessage());
                }

            }
        })).setCoder(SerializableCoder.of(TickerDTO.class));
    }
}
