package maow.javasdf.attribute.holder;

import maow.javasdf.attribute.types.NestedAttribute;

import java.util.List;

public interface NestedHolder {
    List<NestedAttribute> getNestedAttributes();
    NestedAttribute getNestedAttribute(int index);
    void addNestedAttribute(NestedAttribute nestedAttribute);
}
