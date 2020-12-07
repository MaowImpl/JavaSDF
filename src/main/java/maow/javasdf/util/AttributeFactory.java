package maow.javasdf.util;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.attribute.types.BasicAttribute;
import maow.javasdf.attribute.types.CategoryAttribute;
import maow.javasdf.attribute.types.InnerAttribute;
import maow.javasdf.attribute.types.NestedAttribute;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class AttributeFactory {
    private static final Map<String, BiFunction<String, String, Attribute>> factories = new HashMap<>();

    private AttributeFactory() {}

    public static Attribute getAttribute(String prefix, String[] properties) {
        final BiFunction<String, String, Attribute> factory = factories.get(prefix);
        if (factory == null) {
            return new BasicAttribute(properties[0], properties[1]);
        }
        return factory.apply(properties[0], properties[1]);
    }

    public static void addFactory(String prefix, BiFunction<String, String, Attribute> factory) {
        factories.put(prefix, factory);
    }

    static {
        addFactory(".", InnerAttribute::new);
        addFactory("!", CategoryAttribute::new);
        addFactory(";", NestedAttribute::new);
    }
}
