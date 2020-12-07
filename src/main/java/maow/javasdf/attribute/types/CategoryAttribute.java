package maow.javasdf.attribute.types;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.attribute.ParentAttribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;
import java.util.List;

public class CategoryAttribute extends ParentAttribute {
    public CategoryAttribute(String name) {
        super(name);
    }

    public CategoryAttribute(String name, String value) {
        super(name, value);
    }

    @Override
    public void write(SDFWriter writer) throws IOException {
        writer.writeCategoryAttribute(this);
    }

    @Override
    public void read(SDFReader reader, List<Attribute> attributes) {
        reader.resetNestedTree();
        reader.setActiveAttribute(this);
        reader.addCategoryAttribute(this);
        attributes.add(this);
    }
}
