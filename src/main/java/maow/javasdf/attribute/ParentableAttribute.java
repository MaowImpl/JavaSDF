package maow.javasdf.attribute;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentableAttribute extends AbstractAttribute {
    protected final List<NestedAttribute> nestedAttributes = new ArrayList<>();

    public ParentableAttribute(String name, String value) {
        super(name, value);
    }

    public ParentableAttribute(String name) {
        super(name);
    }

    public List<NestedAttribute> getNestedAttributes() {
        return nestedAttributes;
    }

    public NestedAttribute getNestedAttribute(int index) {
        return nestedAttributes.get(index);
    }

    public void addNestedAttribute(NestedAttribute nestedAttribute) {
        nestedAttributes.add(nestedAttribute);
    }
}
