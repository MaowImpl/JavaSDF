package maow.javasdf.attribute;

import maow.javasdf.attribute.holder.NestedHolder;
import maow.javasdf.attribute.types.NestedAttribute;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentAttribute extends AbstractAttribute implements NestedHolder {
    private final List<NestedAttribute> nestedAttributes = new ArrayList<>();

    public ParentAttribute(String name, String value) {
        super(name, value);
    }

    public ParentAttribute(String name) {
        super(name);
    }

    @Override
    public List<NestedAttribute> getNestedAttributes() {
        return nestedAttributes;
    }

    @Override
    public NestedAttribute getNestedAttribute(int index) {
        return nestedAttributes.get(index);
    }

    @Override
    public void addNestedAttribute(NestedAttribute nestedAttribute) {
        nestedAttributes.add(nestedAttribute);
    }
}
