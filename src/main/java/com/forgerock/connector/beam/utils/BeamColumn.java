package com.forgerock.connector.beam.utils;
import org.apache.beam.sdk.schemas.Schema;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BeamColumn {
        String name() default "";
        Class<? extends Schema.FieldType> type() default Schema.FieldType.class;
        boolean nullable() default true;
}
