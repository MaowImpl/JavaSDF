package maow.javasdf.attribute;

import maow.javasdf.attribute.holder.InnerHolder;
import maow.javasdf.attribute.types.InnerAttribute;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAttribute implements Attribute, InnerHolder {
    private final String name;
    private String value;
    private final List<InnerAttribute> innerAttributes = new ArrayList<>();

    public AbstractAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public AbstractAttribute(String name) {
        this(name, "");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public List<InnerAttribute> getInnerAttributes() {
        return innerAttributes;
    }

    @Override
    public InnerAttribute getInnerAttribute(int index) {
        return innerAttributes.get(index);
    }

    @Override
    public void addInnerAttribute(InnerAttribute innerAttribute) {
        innerAttributes.add(innerAttribute);
    }
}
