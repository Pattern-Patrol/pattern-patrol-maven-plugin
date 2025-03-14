package org.patternpatrol.service;

import org.patternpatrol.enums.DirectoryPattern;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.patternpatrol.model.FileAndPath;
import org.patternpatrol.model.FileAndPathList;
import org.patternpatrol.service.impl.DirectoryPatternService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
 

public class DirectoryPatternsServiceTest {

    public static Stream<? extends Arguments> directoryPatternSuccess() {
        return Stream.of(
                Arguments.of("repository", DirectoryPattern.LAYERED, null, null, null, "repository"),
                Arguments.of("service", DirectoryPattern.LAYERED, null, null, null, "service"),
                Arguments.of("controller", DirectoryPattern.LAYERED, null, null, null, "controller"),
                Arguments.of("model", DirectoryPattern.LAYERED, null, null, null, "model"),
                Arguments.of("helper", DirectoryPattern.LAYERED, null, null, null, "helper"),
                Arguments.of("util", DirectoryPattern.LAYERED, null, null, null, "util"),
                Arguments.of("exception", DirectoryPattern.LAYERED, null, null, null, "exception"),
                Arguments.of("enums", DirectoryPattern.LAYERED, null, null, null, "enums"),
                Arguments.of("domain", DirectoryPattern.HEXAGONAL, null, null, null, "domain"),
                Arguments.of("application", DirectoryPattern.HEXAGONAL, null, null, null, "nested/application"),
                Arguments.of("infrastructure", DirectoryPattern.HEXAGONAL, null, null, null, "further/nested/infrastructure"),
                Arguments.of("configuration", DirectoryPattern.HEXAGONAL, null, null, null, "even/more/nested/configuration"),
                Arguments.of("entity", DirectoryPattern.HEXAGONAL, null, null, null, "domain/entity"),
                Arguments.of("usecase", DirectoryPattern.HEXAGONAL, null, null, null, "nested/application/usecase"),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("test"))), "test"),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("test"))),"test")
        );
    }

    public static Stream<? extends Arguments> directoryPatternFails() {
        return Stream.of(
                Arguments.of("repositories", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("services", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("whatever", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("feature", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("patternpatrol", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("notTest"))))
        );
    }

    @ParameterizedTest
    @MethodSource("directoryPatternSuccess")
    public void testShouldSucceedWithCorrectPackagePatterns(
            String fileName,
            DirectoryPattern directoryPattern,
            String arg,
            Set<String> args,
            Set<String> ignore,
            String fullPath) {

        // Given
        FileAndPathList<String, String> fileNames = new FileAndPathList<>();
        fileNames.add(new FileAndPath<>(fileName, fullPath));
        DirectoryRule directoryRule = new DirectoryRule();
        directoryRule.setPatternArg(arg);
        directoryRule.setPatternArgs(args);
        directoryRule.setIgnorePackages(ignore);
        directoryRule.setPattern(directoryPattern);
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, directoryPattern, fileNames, new ArrayList<>());

        // Then
        assertEquals(1, checks.size());
        assertTrue(checks.get(0).isSuccess());
    }


    @ParameterizedTest
    @MethodSource("directoryPatternFails")
    public void testShouldFailWithIncorrectPackagePatterns(
            String fileName,
            DirectoryPattern directoryPattern,
            String arg,
            Set<String> args,
            Set<String> ignore) {

        // Given
        FileAndPathList<String, String> fileNames = new FileAndPathList<>();
        fileNames.add(new FileAndPath<>(fileName, fileName));
        DirectoryRule directoryRule = new DirectoryRule();
        directoryRule.setPatternArg(arg);
        directoryRule.setPatternArgs(args);
        directoryRule.setIgnorePackages(ignore);
        directoryRule.setPattern(directoryPattern);
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, directoryPattern, fileNames, new ArrayList<>());

        // Then
        assertEquals(1, checks.size());
        assertFalse(checks.get(0).isSuccess());
    }
}
