package org.ntua.FlinkStreamJob.sources;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.configuration.Configuration;

import java.util.Random;

public class SyntheticSource extends RichParallelSourceFunction<Tuple2<String, Integer>> {
    private volatile boolean running = true;
    private final Random random = new Random();

    @Override
    public void run(SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
        while(running) {
            String key = "key-" + random.nextInt(1_000_000);
            Integer value = random.nextInt(100);
            ctx.collect(new Tuple2<>(key, value));
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
