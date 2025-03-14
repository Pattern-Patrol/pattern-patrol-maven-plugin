package org.patternpatrol.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HexagonalConstants {

    public static final String ROOT = "root";
    private HexagonalConstants() {
    }

    private static final Map<String, List<String>> HEXAGONAL_STRUCTURE = new HashMap<>();

    static final Map<String, List<String>> PACKAGE_HIERARCHY = new HashMap<>();

    static {
        PACKAGE_HIERARCHY.put("domain", Collections.singletonList(ROOT));
        PACKAGE_HIERARCHY.put("application", Collections.singletonList(ROOT));
        PACKAGE_HIERARCHY.put("infrastructure", Collections.singletonList(ROOT));
        PACKAGE_HIERARCHY.put("configuration", Collections.singletonList(ROOT));

        PACKAGE_HIERARCHY.put("entity", Collections.singletonList("domain"));
        PACKAGE_HIERARCHY.put("event", Collections.singletonList("domain"));
        PACKAGE_HIERARCHY.put("service", Collections.singletonList("domain"));
        PACKAGE_HIERARCHY.put("valueobject", Collections.singletonList("domain"));

        PACKAGE_HIERARCHY.put("usecase", Collections.singletonList("application"));
        PACKAGE_HIERARCHY.put("port", Collections.singletonList("application"));

        PACKAGE_HIERARCHY.put("input", Collections.singletonList("port"));
        PACKAGE_HIERARCHY.put("output", Collections.singletonList("port"));

        PACKAGE_HIERARCHY.put("adapter", Collections.singletonList("infrastructure"));
        PACKAGE_HIERARCHY.put("repository", Collections.singletonList("infrastructure"));
        PACKAGE_HIERARCHY.put("client", Collections.singletonList("infrastructure"));

        PACKAGE_HIERARCHY.put("messaging", Arrays.asList("infrastructure", "configuration"));

        PACKAGE_HIERARCHY.put("rest", Collections.singletonList("adapter"));
        PACKAGE_HIERARCHY.put("cli", Collections.singletonList("adapter"));
        PACKAGE_HIERARCHY.put("graphql", Collections.singletonList("adapter"));
        PACKAGE_HIERARCHY.put("eventhandler", Collections.singletonList("adapter"));

        PACKAGE_HIERARCHY.put("persistence", Collections.singletonList("configuration"));
        PACKAGE_HIERARCHY.put("security", Collections.singletonList("configuration"));
        PACKAGE_HIERARCHY.put("general", Collections.singletonList("configuration"));
    }


    public static Map<String, List<String>> getHexagonalStructure() {
        return PACKAGE_HIERARCHY;
    }
}
