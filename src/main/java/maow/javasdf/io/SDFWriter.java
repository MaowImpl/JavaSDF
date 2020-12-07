package maow.javasdf.io;

import maow.javasdf.attribute.*;
import maow.javasdf.attribute.holder.InnerHolder;
import maow.javasdf.attribute.holder.NestedHolder;
import maow.javasdf.attribute.types.CategoryAttribute;
import maow.javasdf.attribute.types.InnerAttribute;
import maow.javasdf.attribute.types.NestedAttribute;
import maow.javasdf.document.Document;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class SDFWriter extends BufferedWriter {
    private boolean documentNamed = false;
    private boolean headerEnded = false;

    public SDFWriter(Writer out) {
        super(out);
    }

    public void writeDocument(Document document) throws IOException {
        writeDocumentName(document.getName());
        writeInnerAttributes(document.getRootInnerAttributes().toArray(new InnerAttribute[0]));
        writeHeaderEnd();
        for (Attribute attribute : document.getAttributes()) {
            attribute.write(this);
        }
    }

    public void writeDocumentName(String name) throws IOException {
        if (!documentNamed) {
            write(name);
            newLine();
            documentNamed = true;
            return;
        }
        throw new IOException("'writeDocumentName(String)' called twice in SDFWriter, should only happen once.");
    }

    public void writeHeaderEnd() throws IOException {
        if (!headerEnded) {
            write('-');
            newLine();
            headerEnded = true;
            return;
        }
        throw new IOException("'writeHeaderEnd()' called twice in SDFWriter, should only happen once.");
    }

    public void writeAttributes(Attribute... attributes) throws IOException {
        for (Attribute attribute : attributes) {
            writeAttribute(attribute);
        }
    }

    public void writeAttribute(Attribute attribute) throws IOException {
        String delimiter = (attribute.getValue().length() > 0) ? ":" : "";
        write(String.format("%-20s %s", attribute.getName(), delimiter + " " + attribute.getValue()));
        writeInnerAttributes(attribute);
    }

    public void writeInnerAttributes(Attribute attribute) throws IOException {
        if (attribute instanceof InnerHolder) {
            newLine();
            InnerHolder innerHolder = (InnerHolder) attribute;
            writeInnerAttributes(innerHolder.getInnerAttributes().toArray(new InnerAttribute[0]));
        }
    }

    public void writeInnerAttributes(InnerAttribute... innerAttributes) throws IOException {
        for (InnerAttribute innerAttribute : innerAttributes) {
            writeInnerAttribute(innerAttribute);
        }
    }

    public void writeInnerAttribute(InnerAttribute innerAttribute) throws IOException {
        writeAttribute(innerAttribute);
        newLine();
    }

    public void writeCategoryAttributes(CategoryAttribute... categoryAttributes) throws IOException {
        for (CategoryAttribute categoryAttribute : categoryAttributes) {
            writeCategoryAttribute(categoryAttribute);
        }
    }

    public void writeCategoryAttribute(CategoryAttribute categoryAttribute) throws IOException {
        writeAttribute(categoryAttribute);
        writeNestedAttributes(categoryAttribute.getNestedAttributes().toArray(new NestedAttribute[0]));
    }

    public void writeNestedAttributes(Attribute attribute) throws IOException {
        if (attribute instanceof NestedHolder) {
            newLine();
            NestedHolder nestedHolder = (NestedHolder) attribute;
            writeNestedAttributes(nestedHolder.getNestedAttributes().toArray(new NestedAttribute[0]));
        }
    }

    public void writeNestedAttributes(NestedAttribute... nestedAttributes) throws IOException {
        for (NestedAttribute nestedAttribute : nestedAttributes) {
            writeNestedAttribute(nestedAttribute);
        }
    }

    public void writeNestedAttribute(NestedAttribute nestedAttribute) throws IOException {
        writeAttribute(nestedAttribute);
        writeNestedAttributes(nestedAttribute.getNestedAttributes().toArray(new NestedAttribute[0]));
    }

    public void writeComment(String comment) throws IOException {
        write("# " + comment);
        newLine();
    }
}
