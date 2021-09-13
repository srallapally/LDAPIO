package com.forgerock.connector.beam;

import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.RowCoder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.beam.vendor.guava.v26_0_jre.com.google.common.base.Preconditions.checkArgument;

@Experimental(Experimental.Kind.SOURCE_SINK)
@SuppressWarnings({
        "rawtypes", // TODO(https://issues.apache.org/jira/browse/BEAM-10556)
        "nullness" // TODO(https://issues.apache.org/jira/browse/BEAM-10402)
})
public class DummyIO {
    private static final Logger LOG = LoggerFactory.getLogger(DummyIO.class);

    private DummyIO(){}

    public static Read read(){
        return new AutoValue_DummyIO_Read.Builder ()
                .build();
    }

    @FunctionalInterface
    public interface RowMapper<T> extends Serializable {
        T mapRow() throws Exception;
    }

    @AutoValue
    public abstract static class Read<ParameterT, OutputT>
            extends PTransform<PCollection<ParameterT>, PCollection<OutputT>> {
        abstract @Nullable RowMapper<OutputT> getRowMapper();
        abstract @Nullable Coder<OutputT> getCoder();
        abstract Builder<ParameterT, OutputT> builder();

        @AutoValue.Builder
        abstract static class Builder<ParameterT, OutputT>  {
            abstract Read.Builder<ParameterT, OutputT> setRowMapper(RowMapper<OutputT> rowMapper);
            abstract Read.Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder);
            abstract Read<ParameterT, OutputT>  build();
        }

        public Read<ParameterT, OutputT>  withRowMapper(RowMapper<OutputT> rowMapper) {
            checkArgument(rowMapper != null, "Row Mapper can not be null");
            return builder().setRowMapper(rowMapper).build();
        }

        public Read<ParameterT, OutputT>  withCoder(Coder<OutputT> coder) {
            checkArgument(coder != null, "coder can not be null");
            return builder().setCoder(coder).build();
        }

        public PCollection<OutputT> expand(PCollection<ParameterT> input) {
            PCollection<OutputT> output =
                    input.apply(ParDo.of(new ReadFn<>(null, getRowMapper()))).setCoder(getCoder());
            return output;
        }

    }

    private static class ReadFn<ParameterT, OutputT> extends DoFn<ParameterT, OutputT> {
        private final ValueProvider<KV<String,String>> config;
        private final RowMapper<OutputT> rowMapper;

        private ReadFn(ValueProvider<KV<String, String>> config, RowMapper<OutputT> rowMapper){
            this.config = config;
            this.rowMapper = rowMapper;
        }

        @Setup
        public void setup() throws Exception {
            //do something here like get a connection
        }

        @ProcessElement
        public void processElement(ProcessContext context) throws Exception {
            List<ArrayList> res = null;
            context.output(rowMapper.mapRow());
        }

        @FinishBundle
        public void finishBundle() throws Exception {
            cleanUpConnection();
        }

        @Override
        protected void finalize() throws Throwable {
            cleanUpConnection();
        }

        private void cleanUpConnection() throws Exception {
            //do close and clean up
        }
    }
}
