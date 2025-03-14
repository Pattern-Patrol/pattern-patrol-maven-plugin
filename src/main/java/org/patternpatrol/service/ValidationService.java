package org.patternpatrol.service;

import org.patternpatrol.exception.ValidationException;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.Config;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.model.FileAndPath;
import org.patternpatrol.model.FileAndPathList;
import org.patternpatrol.model.FileRule;
import org.patternpatrol.service.impl.DirectoryPatternService;
import org.patternpatrol.service.impl.FilePatternService;
import org.patternpatrol.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ValidationService {

    private final DirectoryPatternService directoryPatternService = new DirectoryPatternService();
    private final FilePatternService filePatternService = new FilePatternService();

    public List<CheckResult> validate(final Config config) throws ValidationException {
        try {
            List<CheckResult> checks = new ArrayList<>();
            FileAndPathList<String, String> reducedPaths = FileUtils.getAllPackagesAtBase(config);
            // Validate directories
            if (config.getDirectoriesRule() != null) {
                validateFilesAndPackages(config.getFileRule(), config.getDirectoriesRule(), reducedPaths, checks);
            }

            return checks;
        } catch (IOException ioe) {
            throw new ValidationException(ioe.getMessage(), ioe);
        }
    }

    private List<CheckResult> validateFilesAndPackages(final FileRule fileRule, final DirectoryRule directoryRule, final FileAndPathList<String, String> files, final List<CheckResult> checks) {
        List<CheckResult> updatedChecks = checks;
        if (fileRule != null) {
            FileAndPathList<String, String> fileNames = getFileNames(files.getFiles().stream().collect(Collectors.toList()));
            updatedChecks = filePatternService.validate(fileRule, fileRule.getNaming(), fileNames, updatedChecks);
        }
        // Validate directories
        if (directoryRule != null) {
            FileAndPathList<String, String> packageNames = getPackageNames(files.getFiles().stream().collect(Collectors.toList()));
            updatedChecks = directoryPatternService.validate(directoryRule, directoryRule.getPattern(), packageNames, updatedChecks);
        }

        if (directoryRule.getDirectoriesRule() != null) {
            // Recursively call validate files and packages to handle nested directory config
            FileAndPathList<String, String> nextLevelFiles = getNextPackageLevel(files.getFiles().stream().collect(Collectors.toList()));
            return validateFilesAndPackages(directoryRule.getFileRule(), directoryRule.getDirectoriesRule(), nextLevelFiles, updatedChecks);
        }

        return updatedChecks;
    }

    private FileAndPathList<String, String> getNextPackageLevel(final List<String> files) {
        return files.stream()
                .filter(file -> file.contains("/")) // Ensure valid paths with '/'
                .map(file -> {
                    String nextPackageLevel = Arrays.stream(file.split("/"))
                            .skip(1) // Skip the first element
                            .collect(Collectors.joining("/")); // Join the rest back with "/"

                    return new FileAndPath<>(nextPackageLevel, file); // Create FileAndPath object
                })
                .filter(entry -> StringUtils.isNotBlank(entry.getFile())) // Ensure non-empty next package level
                .collect(Collectors.toCollection(FileAndPathList::new)); // Collect into FileAndPathList
    }


    private FileAndPathList<String, String> getPackageNames(final List<String> reducedPaths) {
        return reducedPaths.stream()
                .filter(path -> path.contains("/")) // Ensure valid paths with at least one '/'
                .map(path -> new FileAndPath<String, String>(path.split("/")[0], path))
                .collect(Collectors.toCollection(FileAndPathList::new));
    }

    private FileAndPathList<String, String> getFileNames(final List<String> files) {
        return files.stream() // Process only values (full paths)
                .filter(file -> !file.contains("/"))
                .map(path -> {
                    String[] parts = path.split("/");
                    String fileName = parts.length > 0 ? parts[parts.length - 1] : ""; // Get the last segment
                    String cleanFileName = fileName.replace(".java", ""); // Remove ".java"
                    return new FileAndPath<String, String>(cleanFileName, path); // Pair file name with full path
                })
                .filter(entry -> Objects.nonNull(entry.getFile())) // Ensure file name is not blank
                .collect(Collectors.toCollection(FileAndPathList::new));
    }


}
