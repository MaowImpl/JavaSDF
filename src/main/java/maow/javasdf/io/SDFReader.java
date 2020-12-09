package maow.javasdf.io;

import maow.javasdf.attribute.*;
import maow.javasdf.attribute.holder.InnerHolder;
import maow.javasdf.attribute.holder.NestedHolder;
import maow.javasdf.attribute.types.BasicAttribute;
import maow.javasdf.attribute.types.CategoryAttribute;
import maow.javasdf.attribute.types.InnerAttribute;
import maow.javasdf.attribute.types.NestedAttribute;
import maow.javasdf.document.Document;
import maow.javasdf.util.AttributeFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SDFReader extends BufferedReader {
    private final List<String> lines = getLinesAsList();
    private final List<ParentAttribute> nestedTree = new ArrayList<>();
    private final List<Attribute> attributes = new ArrayList<>();

    private int headerEnd = 0;
    private int nestLevel = 0;
    private Attribute activeAttribute = null;

    public SDFReader(Reader in) {
        super(in);
    }

    public Document readDocument() throws IOException {
        attributes.clear();
        return new Document(readDocumentName(), readRootInnerAttributes(), readAttributes());
    }

    public String readDocumentName() throws IOException {
        final String name = lines.get(0);
        if (name.startsWith(";") || name.startsWith(".") || name.startsWith("!") || name.startsWith("-")) {
            throw new IOException("Illegal prefix for document name.");
        }
        return name;
    }

    public List<InnerAttribute> readRootInnerAttributes() {
        final List<InnerAttribute> rootInnerAttributes = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (line.equals("-")) {
                headerEnd = i;
                break;
            }
            rootInnerAttributes.add((InnerAttribute) AttributeFactory.getAttribute(".", getAttributeProperties(line)));
        }
        return rootInnerAttributes;
    }

    public List<Attribute> readAttributes() throws IOException {
        for (int i = headerEnd + 1; i < lines.size(); i++) {
            final Attribute attribute = getAttribute(i);
            if (attribute != null) {
                populateAttributes(attribute);
            }
        }
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    private void populateAttributes(Attribute... attributes) throws IOException {
        for (Attribute attribute : attributes) {
            attribute.read(this);
        }
    }

    public void resetNestedTree() {
        nestLevel = 0;
        nestedTree.clear();
    }

    public Attribute getAttribute(int index) {
        final String line = lines.get(index);
        if (line.startsWith("#") || line.length() == 0) return null;
        final String[] attributeProperties = getAttributeProperties(line);
        return AttributeFactory.getAttribute(line.substring(0, 1), attributeProperties);
    }

    public void setActiveAttribute(Attribute attribute) {
        this.activeAttribute = attribute;
    }

    public void addCategoryAttribute(CategoryAttribute categoryAttribute) {
        nestedTree.add(categoryAttribute);
    }

    public void addInnerAttribute(InnerAttribute innerAttribute) {
        if (activeAttribute != null && activeAttribute instanceof InnerHolder) {
            InnerHolder innerHolder = (InnerHolder) activeAttribute;
            if (nestedTree.size() == 0) {
                innerHolder.addInnerAttribute(innerAttribute);
                return;
            }
            nestedTree.get(nestLevel).addInnerAttribute(innerAttribute);
        }
    }

    public void addNestedAttribute(NestedAttribute nestedAttribute) {
        nestLevel = StringUtils.countMatches(nestedAttribute.getName(), ";");
        while (nestLevel + 1 >= nestedTree.size()) {
            nestedTree.add(null);
        }
        nestedTree.set(nestLevel, nestedAttribute);
        nestedTree.get(nestLevel - 1).addNestedAttribute(nestedAttribute);
    }

    @Override
    public Stream<String> lines() {
        return super.lines().map(s -> s.replaceAll("\\\\n", "\n"));
    }

    public List<String> getLinesAsList() {
        return lines().collect(Collectors.toList());
    }

    private String[] getAttributeProperties(String line) {
        final String[] attributeProperties = new String[2];
        final String[] tempProperties = line.split(":", 2);
        attributeProperties[0] = tempProperties[0].trim();
        attributeProperties[1] = (tempProperties.length == 2) ? tempProperties[1].trim() : "";
        return attributeProperties;
    }
}
