package com.forgerock.connector.beam;

import com.google.auto.value.AutoValue;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.RowCoder;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class TestIO {
    public static <T> Read<T> read() {
        return new AutoValue_TestIO_Read.Builder<T>()
                .build();
    }
    @Experimental(Experimental.Kind.SCHEMAS)
    public static ReadRows readRows() {
        return new AutoValue_TestIO_ReadRows.Builder()
                .build();
    }
    public static <ParameterT, OutputT> ReadAll<ParameterT,OutputT> readAll() {
        return new AutoValue_TestIO_ReadAll.Builder<ParameterT, OutputT>()
                .build();
    }
    @AutoValue
    @Experimental(Experimental.Kind.SCHEMAS)
    public abstract static class ReadRows extends PTransform<PBegin, PCollection<Row>> {
        abstract Builder toBuilder();

        @AutoValue.Builder
        abstract static class Builder {
            abstract ReadRows build();
        }
        
        @Override
        public PCollection<Row> expand(PBegin input) {
            Schema schema = inferBeamSchema();
            PCollection<Row> rows = input.apply(
                    TestIO.<Row>read()
                            .withCoder(RowCoder.of(schema))
                            .withRowMapper(IOSchemaUtils.TestIORowMapper.of(schema))
            );
            rows.setRowSchema(schema);
            return rows;
        }
        private Schema inferBeamSchema(){
            String clazzName = "Application";
            try {
                Class clazz = Class.forName(clazzName);
                return IOSchemaUtils.getSchemaFromBean(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
        @AutoValue
    public abstract static class Read<T> extends PTransform<PBegin, PCollection<T>> {
        abstract @Nullable RowMapper<T> getRowMapper();
        abstract @Nullable Coder<T> getCoder();
        abstract Builder<T> toBuilder();

        @AutoValue.Builder
        abstract static class Builder<T> {
            abstract Builder<T> setRowMapper(RowMapper<T> rowMapper);
            abstract Builder<T> setCoder(Coder<T> coder);
            abstract Read<T> build();
        }

        public Read<T> withRowMapper(RowMapper<T> rowMapper) {
            return toBuilder().setRowMapper(rowMapper).build();
        }

        public Read<T> withCoder(Coder<T> coder) {
            return toBuilder().setCoder(coder).build();
        }

        public PCollection<T> expand(PBegin input) {
            return input.apply(Create.of((Void) null)).apply(TestIO.<Void, T>readAll()
                                    .withCoder(getCoder())
                                    .withRowMapper(getRowMapper()));

        }
    }
    @AutoValue
    public abstract static class ReadAll<ParameterT, OutputT>
            extends PTransform<PCollection<ParameterT>, PCollection<OutputT>> {
        abstract @Nullable RowMapper<OutputT> getRowMapper();
        abstract @Nullable Coder<OutputT> getCoder();

        abstract Builder<ParameterT, OutputT> toBuilder();
        @AutoValue.Builder
        abstract static class Builder<ParameterT, OutputT> {
            abstract Builder<ParameterT, OutputT> setRowMapper(RowMapper<OutputT> rowMapper);
            abstract Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder);
            abstract ReadAll<ParameterT, OutputT> build();
        }

        public ReadAll<ParameterT, OutputT> withRowMapper(RowMapper<OutputT> rowMapper) {
                return toBuilder().setRowMapper(rowMapper).build();
        }
        public ReadAll<ParameterT, OutputT> withCoder(Coder<OutputT> coder) {
            return toBuilder().setCoder(coder).build();
        }

        @Override
        public PCollection<OutputT> expand(PCollection<ParameterT> input) {
            PCollection<OutputT> output = input.apply(ParDo.of(new ReadFn<>(getRowMapper()))).setCoder(getCoder());
            return output;
        }
    }

    private static class ReadFn<ParameterT, OutputT> extends DoFn<ParameterT, OutputT> {
        private final RowMapper<OutputT> rowMapper;

        private ReadFn(RowMapper<OutputT> rowMapper){
            this.rowMapper = rowMapper;
        }
        @Setup
        public void setup()  {
        }

        @ProcessElement
        @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION")
        public void processElement(ProcessContext context) throws Exception {
            System.out.println("Called");
            final List<String> LINES = Arrays.asList(
                    "To be, or not to be: that is the question: ",
                    "Whether 'tis nobler in the mind to suffer ",
                    "The slings and arrows of outrageous fortune, ",
                    "Or to take arms against a sea of troubles, ");
            Iterator i = LINES.listIterator();
            while(i.hasNext()) {
                context.output(rowMapper.mapRow((String) i.next()));
            }
        }
    }

    private TestIO() {}

    @FunctionalInterface
    public interface RowMapper<T> extends Serializable {
        T mapRow(String a) throws Exception;
    }
}
