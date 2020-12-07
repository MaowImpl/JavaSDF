package maow.javasdf.document;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.attribute.types.InnerAttribute;

import java.util.List;

public class Document {
    private final String name;
    private final List<InnerAttribute> rootInnerAttributes;
    private final List<Attribute> attributes;

    public Document(String name, List<InnerAttribute> rootInnerAttributes, List<Attribute> attributes) {
        this.name = name;
        this.rootInnerAttributes = rootInnerAttributes;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public InnerAttribute getRootInnerAttribute(int index) {
        return rootInnerAttributes.get(index);
    }

    public Attribute getAttribute(int index) {
        return attributes.get(index);
    }

    public List<InnerAttribute> getRootInnerAttributes() {
        return rootInnerAttributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addRootInnerAttribute(InnerAttribute innerAttribute) {
        rootInnerAttributes.add(innerAttribute);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }
}
