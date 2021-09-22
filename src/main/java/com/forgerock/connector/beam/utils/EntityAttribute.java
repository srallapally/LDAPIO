package com.forgerock.connector.beam.utils;

import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

@DefaultCoder(AvroCoder.class)
public class EntityAttribute implements Serializable {
    private static final long serialVersionUID = 4952546786156940115L;
    @Nullable String attributeName;
    @Nullable String attributeValue;

    public EntityAttribute(){}

    public EntityAttribute(String attributeName, String attributeValue){
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    public String getAttributeValue() {
        return attributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityAttribute entityAttribute = (EntityAttribute) o;
        return attributeName.equals(entityAttribute.attributeName) && attributeValue.equals(entityAttribute.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, attributeValue);
    }
}
