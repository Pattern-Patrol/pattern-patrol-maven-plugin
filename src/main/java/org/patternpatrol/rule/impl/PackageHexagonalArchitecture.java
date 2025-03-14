package org.patternpatrol.rule.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.patternpatrol.constant.HexagonalConstants;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;

import static org.patternpatrol.constant.HexagonalConstants.ROOT;


public class PackageHexagonalArchitecture implements DirectoryPatternRule {

    private static final Map<String, List<String>> HEXAGONAL_STRUCTURE = HexagonalConstants.getHexagonalStructure();

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        return checkPathIsValid(directoryRule, targetPath, targetPath, fullPath);
    }

    private CheckResult checkPathIsValid(final DirectoryRule directoryRule, final String initialDirectory, final String directory, final String fullPath) {
        List<String> parentDirectory = HEXAGONAL_STRUCTURE.get(directory);

        if (parentDirectory == null) {
            String errorMessage = String.format("Package '%s' is not allowed in the hexagonal architecture.", directory);
            return new CheckResult(false, directory, directoryRule.getLevel(), errorMessage);

        } else if (parentDirectory.contains(ROOT)) {
            return new CheckResult(true, initialDirectory, directoryRule.getLevel(), "Package " + initialDirectory + " follows correct directory pattern");

        } else {
            // **Find the parent directory in the fullPath**
            String[] pathParts = fullPath.split("/");
            int index = Arrays.asList(pathParts).indexOf(directory);

            if (index > 0) { // Ensure there's a parent
                String parent = pathParts[index - 1]; // Get the previous segment
                return checkPathIsValid(directoryRule, initialDirectory, parent, fullPath);
            } else {
                // No valid parent found, return an error
                String errorMessage = String.format("Invalid package structure for '%s'. Could not find a parent directory.", directory);
                return new CheckResult(false, directory, directoryRule.getLevel(), errorMessage);
            }
        }
    }
}
