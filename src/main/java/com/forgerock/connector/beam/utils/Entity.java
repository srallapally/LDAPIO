package com.forgerock.connector.beam.utils;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@DefaultCoder(AvroCoder.class)
public class Entity implements Serializable {
    private static final long serialVersionUID = 7068651458539895375L;
    private  String _id;
    private Set<EntityAttribute> entityAttributes;

    public Entity(){}
    public Entity(String id, Set<EntityAttribute> entityAttributes) {
        this.entityAttributes = entityAttributes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Set<EntityAttribute> getAttributes() {
        return entityAttributes;
    }

    public void setAttributes(Set<EntityAttribute> entityAttributes) {
        this.entityAttributes = entityAttributes;
    }

    public void addAttribute(EntityAttribute entityAttribute){
        if(entityAttributes == null || entityAttributes.size() == 0)
            entityAttributes = new HashSet<EntityAttribute>();
        entityAttributes.add(entityAttribute);
    }

    public void addAttribute(String name, String value){
        if(entityAttributes == null || entityAttributes.size() == 0)
            entityAttributes = new HashSet<EntityAttribute>();
        EntityAttribute a = new EntityAttribute(name,value);
        entityAttributes.add(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return _id.equals(entity._id) && entityAttributes.equals(entity.entityAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, entityAttributes);
    }
}
