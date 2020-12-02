package maow.javasdf.attribute;

import java.util.Collection;
import java.util.Collections;

public class InnerAttribute extends AbstractAttribute {
    public InnerAttribute(String name, String value) {
        super(name, value);
    }

    public InnerAttribute(String name) {
        super(name);
    }

    @Override
    public Collection<InnerAttribute> getInnerAttributes() {
        return Collections.emptyList();
    }

    @Override
    public InnerAttribute getInnerAttribute(String name) {
        return null;
    }

    @Override
    public void addInnerAttribute(InnerAttribute innerAttribute) {}
}
