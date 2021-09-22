package com.forgerock.connector.beam.ldap;

import static com.google.common.base.Preconditions.checkArgument;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.forgerock.connector.beam.utils.Entity;
import com.forgerock.connector.beam.utils.EntityAttribute;
import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.display.DisplayData;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class LdapIO {
    private static final Logger LOG = LoggerFactory.getLogger(LdapIO.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static <T> LdapIO.Read<T> read() {
        return new AutoValue_LdapIO_Read.Builder<T>()
                .build();
    }
    @AutoValue
    public abstract static class Read<T> extends PTransform<PBegin, PCollection<T>> {
        @Nullable
        abstract LdapConfiguration getLdapConfiguration();

        @Nullable
        abstract RowMapper<T> getRowMapper();

        @Nullable
        abstract Coder<T> getCoder();

        abstract Builder<T> toBuilder();

        @AutoValue.Builder
        abstract static class Builder<T> {
            abstract Builder<T> setLdapConfiguration(LdapConfiguration config);

            abstract Builder<T> setRowMapper(RowMapper<T> rowMapper);

            abstract Builder<T> setCoder(Coder<T> coder);

            abstract Read<T> build();
        }

        public Read<T> withLdapConfiguration(LdapConfiguration config) {
            checkArgument(config != null,
                    "withLdapConfiguration(configuration) called with null configuration");
            return toBuilder().setLdapConfiguration(config).build();
        }

        public Read<T> withRowMapper(RowMapper<T> rowMapper) {
            checkArgument(rowMapper != null, ".withRowMapper(rowMapper) called with null rowMapper");
            return toBuilder().setRowMapper(rowMapper).build();
        }

        public Read<T> withCoder(Coder<T> coder) {
            checkArgument(coder != null, "withCoder(coder) called with null coder");
            return toBuilder().setCoder(coder).build();
        }

        @Override
        public PCollection<T> expand(PBegin input) {
            return input
                    .apply(Create.of((Void) null))
                    .apply(
                         ParDo.of(
                             new ReadFn<>(
                                     getLdapConfiguration(),getRowMapper())))
                    .setCoder(getCoder())
                    .apply( new Reparallelize<>());
        }

        @Override
        public void populateDisplayData(DisplayData.Builder builder) {
            super.populateDisplayData(builder);
            builder.add(DisplayData.item("rowMapper", getRowMapper().getClass().getName()));
            builder.add(DisplayData.item("coder", getCoder().getClass().getName()));
        }
    }
    @AutoValue
    public abstract static class LdapConfiguration implements Serializable {
        @Nullable
        abstract ValueProvider<String> getHost();

        @Nullable
        abstract ValueProvider<Integer> getPort();

        @Nullable
        abstract ValueProvider<Boolean> getIsSsl();

        @Nullable
        abstract ValueProvider<String> getPrincipal();

        @Nullable
        abstract ValueProvider<String> getPassword();

        @Nullable
        abstract ValueProvider<String> getBaseContexts();

        @Nullable
        abstract ValueProvider<String> getBaseContextsToSearch();

        @Nullable
        abstract ValueProvider<String> getUserSearchFilter();

        @Nullable
        abstract ValueProvider<String> getAttributesToGet();

        @Nullable
        abstract ValueProvider<String> getGroupSearchFilter();

        @Nullable
        abstract ValueProvider<Boolean> getUseBlocks();

        @Nullable
        abstract ValueProvider<String> getGroupMemberAttribute();

        @Nullable
        abstract ValueProvider<Integer> getBlockSize();

        @Nullable
        abstract ValueProvider<Boolean> getUsePagedResultControl();

        @Nullable
        abstract ValueProvider<String> getVlvSortAttribute();

        @Nullable
        abstract ValueProvider<String> getUidAttribute();

        abstract Builder builder();

        @AutoValue.Builder
        abstract static class Builder {
            abstract Builder setPort(ValueProvider<Integer> port);
            abstract Builder setHost(ValueProvider<String> host);
            abstract Builder setIsSsl(ValueProvider<Boolean> isSsl);
            abstract Builder setPrincipal(ValueProvider<String> principal);
            abstract Builder setPassword(ValueProvider<String> password);
            abstract Builder setBaseContexts(ValueProvider<String> baseContexts);
            abstract Builder setBaseContextsToSearch(ValueProvider<String> baseContextsToSearch);
            abstract Builder setUserSearchFilter(ValueProvider<String> setUserSearchFilter);
            abstract Builder setAttributesToGet(ValueProvider<String> attributesToGet);
            abstract Builder setGroupSearchFilter(ValueProvider<String> groupSearchFilter);
            abstract Builder setUseBlocks(ValueProvider<Boolean> useBlocks);
            abstract Builder setGroupMemberAttribute(ValueProvider<String> groupMemberAttribute);
            abstract Builder setBlockSize(ValueProvider<Integer> blockSize);
            abstract Builder setUsePagedResultControl(ValueProvider<Boolean> usePageResultControl);
            abstract Builder setVlvSortAttribute(ValueProvider<String> vlvSortAttribute);
            abstract Builder setUidAttribute(ValueProvider<String> uidAttribute);
            abstract LdapConfiguration build();
        }

        public static LdapConfiguration create (ValueProvider<String> host,ValueProvider<Integer> port) {
                checkArgument(host != null, "Host can not be null");
                checkArgument(port != null, "Port can not be null");
                return new AutoValue_LdapIO_LdapConfiguration.Builder()
                        .setHost(host)
                        .setPort(port)
                        .build();
        }

        public LdapConfiguration withPrincipal(ValueProvider<String> principal){
            return builder().setPrincipal(principal).build();
        }

        public LdapConfiguration withPassword(ValueProvider<String> password){
            return builder().setPassword(password).build();
        }

        public LdapConfiguration withIsSSL(ValueProvider<Boolean> isSSL){
            return builder().setIsSsl(isSSL).build();
        }

        public LdapConfiguration withBaseContexts(ValueProvider<String> baseContexts){
            return builder().setBaseContexts(baseContexts).build();
        }

        public LdapConfiguration withBaseContextsToSearch(ValueProvider<String> baseContextsToSearch){
            return builder().setBaseContextsToSearch(baseContextsToSearch).build();
        }
        public LdapConfiguration withUserSearchFilter(ValueProvider<String> userSearchFilter) {
            return builder().setUserSearchFilter(userSearchFilter).build();
        }
        public LdapConfiguration withAttributesToGet(ValueProvider<String> attributesToGet){
            return builder().setAttributesToGet(attributesToGet).build();
        }
        public LdapConfiguration withGroupSearchFilter(ValueProvider<String> groupSearchFilter){
            return builder().setGroupSearchFilter(groupSearchFilter).build();
        }
        public LdapConfiguration withUseBlocks(ValueProvider<Boolean> useBlocks){
            return builder().setUseBlocks(useBlocks).build();
        }
        public LdapConfiguration withGroupMemberAttribute(ValueProvider<String> groupMemberAttribute){
            return builder().setGroupMemberAttribute(groupMemberAttribute).build();
        }
        public LdapConfiguration withBlockSize(ValueProvider<Integer> blockSize){
            return builder().setBlockSize(blockSize).build();
        }
        public LdapConfiguration withUsePagedResultControl(ValueProvider<Boolean> usePageResultControl){
            return builder().setUsePagedResultControl(usePageResultControl).build();
        }
        public LdapConfiguration withVlvSortAttribute(ValueProvider<String> vlvSortAttribute){
            return builder().setVlvSortAttribute(vlvSortAttribute).build();
        }
        public LdapConfiguration  withUidAttribute(ValueProvider<String> uidAttribute){
            return builder().setUidAttribute(uidAttribute).build();
        }
        private void populateDisplayData(DisplayData.Builder builder) {
            builder.addIfNotNull(DisplayData.item("host", getHost()));
            builder.addIfNotNull(DisplayData.item("port", getPort()));
            builder.addIfNotNull(DisplayData.item("principal", getPrincipal()));
            builder.addIfNotNull(DisplayData.item("baseContexts", getBaseContexts()));
        }

        LdapManager buildLdapManager() {
           BasicLdapConfiguration config = new BasicLdapConfiguration();
           config.setBaseContexts(new String[]{getBaseContexts().get()});
           config.setHost(getHost().get());
           config.setPort(getPort().get());
           config.setBaseContextsToSearch(new String[]{getBaseContextsToSearch().get()});
           config.setPassword(getPassword().get());
           config.setPrincipal(getPrincipal().get());
           config.setUidAttribute(getUidAttribute().get());
           config.setSsl(getIsSsl().get());
           config.setUserSearchFilter(getUserSearchFilter().get());
           config.setBlockSize(getBlockSize().get());
           config.setUsePagedResultControl(getUsePagedResultControl().get());
           config.setVlvSortAttribute(getVlvSortAttribute().get());
           System.out.println(getAttributesToGet().get());
           config.setAttributesToGet(getAttributesToGet().get());
           LdapManager ldapManager = new LdapManager(config);
           return ldapManager;
        }
    }

    private static class ReadFn<X,T> extends DoFn<X,T> {
        private final LdapConfiguration ldapConfiguration;
        private final RowMapper<T> rowMapper;
        private LdapManager ldapManager = null;

        private ReadFn(LdapConfiguration ldapConfiguration,RowMapper<T> rowMapper){
            this.ldapConfiguration = ldapConfiguration;
            this.rowMapper = rowMapper;
        }
        @Setup
        public void setup() throws Exception {
            ldapManager = ldapConfiguration.buildLdapManager();
        }

        @ProcessElement
        public void processElement(ProcessContext context) throws Exception {
            ArrayList<Entity> eList = ldapManager.executeSearch();
            Iterator i = eList.listIterator();
            while (i.hasNext()) {
                Entity e1 = null;
                e1 = (Entity)i.next();
                context.output(rowMapper.mapRow(e1));
                }
        }

        @DoFn.Teardown
        public void teardown() throws Exception {
            //ldapManager.close();
        }

    }

    private static class Reparallelize<T> extends PTransform<PCollection<T>, PCollection<T>> {
        @Override
        public PCollection<T> expand(PCollection<T> input) {
            PCollectionView<Iterable<T>> empty =
                    input.apply("Consume", Filter.by(SerializableFunctions.constant(false)))
                            .apply(View.asIterable());
            PCollection<T> materialized =
                    input.apply(
                            "Identity",
                            ParDo.of(new DoFn<T, T>() {
                                                @ProcessElement
                                                public void process(ProcessContext c) {
                                                    c.output(c.element());
                                                }
                                            })
                                    .withSideInputs(empty));
            return materialized.apply(Reshuffle.viaRandomKey());
        }
    }

    private LdapIO() {}

    @FunctionalInterface
    public interface RowMapper<T> extends Serializable {
        T mapRow(Entity entity) throws Exception;
    }

    public static RowMapper<String> getRowMapper() {
        return new com.forgerock.connector.beam.ldap.LdapIO.RowMapper<String>() {
            @Override
            public String mapRow(Entity entity) throws Exception {
                //System.out.println(LdapIO.class.getSimpleName()+":mapRow...");
                ObjectNode node = MAPPER.createObjectNode();
                if(entity == null){
                    System.out.println(LdapIO.class.getSimpleName()+":mapRow::Oops");
                }
                Set<EntityAttribute> entityAttributes = entity.getAttributes();
                Iterator<EntityAttribute> i = entityAttributes.iterator();
                while(i.hasNext()) {
                    String name = null;
                    String value = null;
                    EntityAttribute ea = null;
                    ea = i.next();
                    name = ea.getAttributeName();
                    value = ea.getAttributeValue();
                    if(name == null) {
                        System.out.println(LdapIO.class.getSimpleName()+":getRowMapper:Danger");
                    } else {
                        node.put(name,value);
                    }
                }
                return node.toString();
            }
        };
    }
}
