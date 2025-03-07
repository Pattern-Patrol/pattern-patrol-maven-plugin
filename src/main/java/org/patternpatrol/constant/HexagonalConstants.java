package org.patternpatrol.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class HexagonalConstants {

    private HexagonalConstants() {
    }

    private static final Set<String> ALLOWED_MODULE_NAMES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    ""
            )));

    public static Set<String> getPackageNames() {
        return ALLOWED_MODULE_NAMES;
    }

    public static Set<String> getFileNames() {
        return ALLOWED_MODULE_NAMES.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toSet());
    }
}
