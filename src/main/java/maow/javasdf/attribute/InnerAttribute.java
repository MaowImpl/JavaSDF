package maow.javasdf.attribute;

import java.util.Collections;
import java.util.List;

public class InnerAttribute extends AbstractAttribute {
    public InnerAttribute(String name, String value) {
        super(name, value);
    }

    public InnerAttribute(String name) {
        super(name);
    }

    @Override
    public List<InnerAttribute> getInnerAttributes() {
        return Collections.emptyList();
    }

    @Override
    public InnerAttribute getInnerAttribute(int index) {
        return null;
    }

    @Override
    public void addInnerAttribute(InnerAttribute innerAttribute) {}
}
