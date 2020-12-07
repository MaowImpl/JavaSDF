package maow.javasdf.attribute.holder;

import maow.javasdf.attribute.types.InnerAttribute;

import java.util.List;

public interface InnerHolder {
    List<InnerAttribute> getInnerAttributes();
    InnerAttribute getInnerAttribute(int index);
    void addInnerAttribute(InnerAttribute innerAttribute);
}
