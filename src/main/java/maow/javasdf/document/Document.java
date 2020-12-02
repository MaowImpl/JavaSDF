package maow.javasdf.document;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.attribute.InnerAttribute;

import java.util.*;

public class Document {
    private final String name;
    private final Map<String, InnerAttribute> rootInnerAttributes;
    private final Map<String, Attribute> attributes;

    public Document(String name, Collection<InnerAttribute> rootInnerAttributes, Collection<Attribute> attributes) {
        this.name = name;
        final Map<String, InnerAttribute> innerMap = new HashMap<>();
        for (InnerAttribute innerAttribute : rootInnerAttributes) {
            innerMap.put(innerAttribute.getName(), innerAttribute);
        }
        this.rootInnerAttributes = innerMap;
        final Map<String, Attribute> map = new HashMap<>();
        for (Attribute attribute : attributes) {
            map.put(attribute.getName(), attribute);
        }
        this.attributes = map;
    }

    public String getName() {
        return name;
    }

    public Collection<InnerAttribute> getRootInnerAttributes() {
        return rootInnerAttributes.values();
    }

    public Collection<Attribute> getAttributes() {
        return attributes.values();
    }

    public InnerAttribute getRootInnerAttribute(String name) {
        return rootInnerAttributes.get(name);
    }

    public Attribute getAttribute(String name) {
        return attributes.get(name);
    }

    public void addRootInnerAttribute(InnerAttribute innerAttribute) {
        rootInnerAttributes.put(innerAttribute.getName(), innerAttribute);
    }

    public void addAttribute(Attribute attribute) {
        attributes.put(attribute.getName(), attribute);
    }
}
