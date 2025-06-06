package org.ntua.FlinkStreamJob.functions;

import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.guava31.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Random;

public class StateAccumulator extends KeyedProcessFunction<String,
        Tuple2<String, Integer>, String> {
    private ListState<Integer
            > state;
    private Random random;

    @Override
    public void open(OpenContext openContext) throws Exception {
        ListStateDescriptor<Integer> listStateDescriptor =
                new ListStateDescriptor<>("values", Integer.class);
        state = getRuntimeContext().getListState(listStateDescriptor);
        random = new Random();
    }

    @Override
    public void processElement(Tuple2<String, Integer> value, KeyedProcessFunction<String,
            Tuple2<String, Integer>, String>.Context ctx, Collector<String> out)
            throws Exception {
        state.add(value.f1);
        if (random.nextInt(1000) == 0) {
            int size = Iterables.size(state.get());
            out.collect("Key: " + value.f0 + ", State size: " + size);
        }
    }


}
