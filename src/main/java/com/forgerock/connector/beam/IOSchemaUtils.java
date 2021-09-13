package com.forgerock.connector.beam;

import com.forgerock.connector.beam.utils.BeamColumn;
import com.forgerock.connector.beam.utils.SchemaColumn;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.values.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class IOSchemaUtils {
    private static transient Logger log = LoggerFactory.getLogger(IOSchemaUtils.class);
    private Map<String, SchemaColumn> columns;

    public void add(SchemaColumn col) {
        this.columns.put(col.getColumnName(), col);
    }

    public String getMethodName(String columnName) {
        return this.columns.get(columnName).getMethodName();
    }

    static Schema getSchemaFromBean(Class<?> c) {
        Schema.Builder schemaBuilder = Schema.builder();
        SchemaColumn col;
        List<String> sfl = new ArrayList<>();

        Method[] methods = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (methodName.toLowerCase().startsWith("get") == false) {
                continue;
            }

            col = new SchemaColumn();
            col.setMethodName(methodName);

            // We have a public method starting with get
            String columnName;
            Schema.FieldType dataType;
            boolean nullable;
            BeamColumn beamColumn = method.getAnnotation(BeamColumn.class);
            if (beamColumn == null) {
                log.debug("No annotation for method {}", methodName);
                columnName = "";
                String typeName = method.getReturnType().getSimpleName();
                switch (typeName) {
                    case "int":
                    case "Integer":
                        schemaBuilder.addInt16Field(buildColumnName(columnName, methodName));
                    case "String":
                        schemaBuilder.addStringField(buildColumnName(columnName, methodName));
                    default:
                        schemaBuilder.addStringField(buildColumnName(columnName, methodName));
                }
                nullable = true;
            } else {
                columnName = beamColumn.name();
                switch (beamColumn.type().getSimpleName()) {
                    case "int":
                    case "Integer":
                        schemaBuilder.addInt16Field(buildColumnName(columnName, methodName));
                    case "String":
                        schemaBuilder.addStringField(buildColumnName(columnName, methodName));
                    default:
                        schemaBuilder.addStringField(buildColumnName(columnName, methodName));
                }
                nullable = beamColumn.nullable();
            }
        }
        return schemaBuilder.build();
    }

    private static String buildColumnName(String columnName, String methodName) {
        if (columnName.length() > 0) {
            return columnName;
        }
        columnName = methodName.substring(3);
        if (columnName.length() == 0) {
            return "_c0";
        }
        return columnName;
    }

    static final class TestIORowMapper implements TestIO.RowMapper<Row>{
        private final Schema schema;

        public static TestIORowMapper of (Schema schema){
            return new TestIORowMapper(schema);
        }

        private TestIORowMapper(Schema schema){
            this.schema = schema;
        }
        @Override
        public Row mapRow(String a) throws Exception {
            return null;
        }
    }
}

