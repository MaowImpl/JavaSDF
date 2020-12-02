package maow.javasdf.io;

import maow.javasdf.attribute.*;
import maow.javasdf.document.Document;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public class SDFWriter {
    private final BufferedWriter writer;

    public SDFWriter(Writer out) {
        this.writer = new BufferedWriter(out);
    }

    public void writeDocument(Document document) throws IOException {
        writer.write(document.getName());
        writer.newLine();
        for (InnerAttribute rootInnerAttribute : document.getRootInnerAttributes()) {
            writer.write(rootInnerAttribute.getName() + " : " + rootInnerAttribute.getValue());
            writer.newLine();
        }
        writer.write('-');
        writer.newLine();
        for (Attribute attribute : document.getAttributes()) {
            if (attribute instanceof AbstractAttribute) {
                writer.write(attribute.getName() + " : " + attribute.getValue());
                writer.newLine();
                for (InnerAttribute innerAttribute : ((AbstractAttribute) attribute).getInnerAttributes()) {
                    writer.write(innerAttribute.getName() + " : " + innerAttribute.getValue());
                    writer.newLine();
                }
                if (attribute instanceof CategoryAttribute) {
                    Collection<NestedAttribute> nestedAttributes = ((CategoryAttribute) attribute).getNestedAttributes();
                    while (nestedAttributes.size() > 0) {
                        for (NestedAttribute nestedAttribute : nestedAttributes) {
                            writer.write(nestedAttribute.getName() + " : " + nestedAttribute.getValue());
                            writer.newLine();
                            nestedAttributes = nestedAttribute.getNestedAttributes();
                            for (InnerAttribute nestedInnerAttribute : nestedAttribute.getInnerAttributes()) {
                                writer.write(nestedInnerAttribute.getName() + " : " + nestedInnerAttribute.getValue());
                                writer.newLine();
                            }
                        }
                    }
                }
            }
        }
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
