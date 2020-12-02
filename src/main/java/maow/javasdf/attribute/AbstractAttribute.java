package maow.javasdf.attribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAttribute implements Attribute {
    private final String name;
    private String value;
    private final Map<String, InnerAttribute> innerAttributes = new HashMap<>();

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

    public Collection<InnerAttribute> getInnerAttributes() {
        return innerAttributes.values();
    }

    public InnerAttribute getInnerAttribute(String name) {
        return innerAttributes.get(name);
    }

    public void addInnerAttribute(InnerAttribute innerAttribute) {
        innerAttributes.put(innerAttribute.getName(), innerAttribute);
    }
}
