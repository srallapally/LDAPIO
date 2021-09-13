package com.forgerock.connector.beam.utils;
import java.io.Serializable;

public class SchemaColumn implements Serializable {
    private static final long serialVersionUID = 4649600553627732273L;
    private String methodName;
    private String columnName;

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String method) {
        this.methodName = method;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName
     *            the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
