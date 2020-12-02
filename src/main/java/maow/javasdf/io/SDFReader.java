package maow.javasdf.io;

import maow.javasdf.attribute.*;
import maow.javasdf.document.Document;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SDFReader {
    private final BufferedReader reader;

    public SDFReader(Reader in) {
        this.reader = new BufferedReader(in);
    }

    public Document getDocument() throws IOException {
        final List<String> lines = getLinesInInput();

        final String name = lines.get(0);
        final List<InnerAttribute> rootInnerAttributes = new ArrayList<>();
        final List<Attribute> attributes = new ArrayList<>();

        int headerEnd = 0;
        for (int i = 1; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (line.equals("-")) {
                headerEnd = i;
                break;
            }
            final String[] attributeProperties = getAttributeProperties(line);
            rootInnerAttributes.add(new InnerAttribute(attributeProperties[0], attributeProperties[1]));
        }

        AbstractAttribute activeAttribute = null;
        final List<AbstractAttribute> nestedTree = new ArrayList<>();
        int indent = 0;
        for (int i = headerEnd + 1; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (!line.startsWith("#")) {
                if (!line.startsWith(".") && !line.startsWith(";")) {
                    if (activeAttribute != null) {
                        attributes.add(activeAttribute);
                        nestedTree.clear();
                    }
                    final String[] attributeProperties = getAttributeProperties(line);
                    if (line.startsWith("!")) {
                        activeAttribute = new CategoryAttribute(attributeProperties[0]);
                        nestedTree.add(activeAttribute);
                        continue;
                    }
                    activeAttribute = new BasicAttribute(attributeProperties[0], attributeProperties[1]);
                } else if (activeAttribute != null) {
                    if (line.startsWith(".")) {
                        final String[] innerAttributeProperties = getAttributeProperties(line);
                        final InnerAttribute innerAttribute = new InnerAttribute(innerAttributeProperties[0], innerAttributeProperties[1]);
                        if (nestedTree.size() == 0) {
                            activeAttribute.addInnerAttribute(innerAttribute);
                            continue;
                        }
                        nestedTree.get(indent).addInnerAttribute(innerAttribute);
                    } else if (line.startsWith(";") && activeAttribute instanceof CategoryAttribute) {
                        final String[] nestedAttributeProperties = getAttributeProperties(line);
                        final NestedAttribute nestedAttribute = new NestedAttribute(nestedAttributeProperties[0], nestedAttributeProperties[1]);
                        indent = StringUtils.countMatches(nestedAttribute.getName(), ";");

                        nestedTree.add(nestedAttribute);
                        final AbstractAttribute attribute = nestedTree.get(indent - 1);
                        if (attribute instanceof CategoryAttribute) {
                            ((CategoryAttribute) attribute).addNestedAttribute(nestedAttribute);
                            continue;
                        }
                        ((NestedAttribute) attribute).addNestedAttribute(nestedAttribute);
                    }
                }
            }
        }

        return new Document(name, rootInnerAttributes, attributes);
    }

    public void close() throws IOException {
        reader.close();
    }

    private List<String> getLinesInInput() throws IOException {
        final List<String> lines = new ArrayList<>();
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        return lines;
    }

    private String[] getAttributeProperties(String line) {
        final String[] tempProperties = new String[2];
        final String[] attributeProperties = line.split(":", 2);
        tempProperties[0] = attributeProperties[0].trim();
        tempProperties[1] = (attributeProperties.length == 2) ? attributeProperties[1].trim() : "";
        return tempProperties;
    }
}
