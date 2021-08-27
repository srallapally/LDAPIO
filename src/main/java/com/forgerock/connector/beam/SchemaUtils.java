package com.forgerock.connector.beam;

import org.apache.beam.sdk.schemas.Schema;

import java.util.ArrayList;
import java.util.List;

class SchemaUtils {

    static Schema toBeamSchema(ArrayList<String> attributes){
        Schema.Builder schemaBuilder = Schema.builder();
        schemaBuilder.addStringField("userName");
        schemaBuilder.addStringField("firstName");
        schemaBuilder.addStringField("lastName");
        return schemaBuilder.build();
    }

    static final class BeamRowMapper implements DummyIO.RowMapper {
        private final Schema schema;

        private BeamRowMapper(Schema schema){
            this.schema = schema;
        }

        public static BeamRowMapper of(Schema schema){
            return new BeamRowMapper(schema);
        }
        @Override
        public List<ArrayList> mapRow() throws Exception {
            List<ArrayList> res = null;
            ArrayList row = new ArrayList();
            row.add("bob.rodgers");
            row.add("Bob");
            row.add("Rodgers");

            res.add(row);
            row.clear();
            row.add("julie.yee");
            row.add("Julie");
            row.add("Yee");
            res.add(row);
            return res;
        }
    }
}
