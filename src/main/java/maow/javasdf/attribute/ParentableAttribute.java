package maow.javasdf.attribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ParentableAttribute extends AbstractAttribute {
    protected final Map<String, NestedAttribute> nestedAttributes = new HashMap<>();

    public ParentableAttribute(String name, String value) {
        super(name, value);
    }

    public ParentableAttribute(String name) {
        super(name);
    }

    public Collection<NestedAttribute> getNestedAttributes() {
        return nestedAttributes.values();
    }

    public NestedAttribute getNestedAttribute(String name) {
        return nestedAttributes.get(name);
    }

    public void addNestedAttribute(NestedAttribute nestedAttribute) {
        nestedAttributes.put(nestedAttribute.getName(), nestedAttribute);
    }
}
