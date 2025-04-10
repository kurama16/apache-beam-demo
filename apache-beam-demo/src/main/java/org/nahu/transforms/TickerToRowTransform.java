package org.nahu.transforms;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.jetbrains.annotations.NotNull;
import org.nahu.dto.TickerDTO;

import static org.nahu.util.RowUtils.buildRow;

public class TickerToRowTransform extends PTransform<PCollection<TickerDTO>, PCollection<String>> {
    @NotNull
    @Override
    public PCollection<String> expand(PCollection<TickerDTO> input) {
        return input.apply("map TickerDTO to csv row", ParDo.of(new DoFn<TickerDTO,String >() {

            @ProcessElement
            public void processElement(@Element TickerDTO tickerDTO, OutputReceiver<String> out) {

                tickerDTO.getResults()
                        .forEach(result -> out.output(buildRow(tickerDTO, result)));

            }
        }));

    }
}
