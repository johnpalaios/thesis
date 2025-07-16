package org.ntua.FlinkStreamJob;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.ntua.FlinkStreamJob.functions.StateAccumulator;
import org.ntua.FlinkStreamJob.sources.SyntheticSource;

public class App2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<Integer, Integer>> dataStream = env.addSource(new SyntheticSource());
        dataStream.keyBy(tuple2 -> tuple2.f0)
                .process(new StateAccumulator())
                .print();
        env.execute("Flink Application 2");
    }
}
