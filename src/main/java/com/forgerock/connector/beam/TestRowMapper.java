package com.forgerock.connector.beam;

import java.util.ArrayList;

public class TestRowMapper implements TestIO.RowMapper {
        @Override
        public String mapRow(String a) throws Exception {
            return a;
        }
}
