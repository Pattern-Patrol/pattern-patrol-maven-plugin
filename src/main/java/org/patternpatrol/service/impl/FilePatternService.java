package org.patternpatrol.service.impl;

import org.patternpatrol.enums.FileNamingStandard;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.FileAndPathList;
import org.patternpatrol.model.FileRule;
import org.patternpatrol.rule.impl.FileEndsWith;
import org.patternpatrol.rule.impl.FileEndsWithLayered;
import org.patternpatrol.rule.impl.FileStartsWith;
import org.patternpatrol.service.PatternService;

import java.util.List;


public class FilePatternService implements PatternService<FileRule, FileNamingStandard> {

    public List<CheckResult> validate(final FileRule rule, final FileNamingStandard fileNamingStandard, final FileAndPathList<String, String> files, final List<CheckResult> existingChecks) {
        List<CheckResult> checks = existingChecks;
        for (String file : files.getFiles()) {
            switch (fileNamingStandard) {
                case ENDS_WITH:
                    FileEndsWith fileEndsWith = new FileEndsWith();
                    checks.add(fileEndsWith.check(rule, file, files.getFileAndPath(file).getPath()));
                    break;
                case STARTS_WITH:
                    FileStartsWith fileStartsWith = new FileStartsWith();
                    checks.add(fileStartsWith.check(rule, file, files.getFileAndPath(file).getPath()));
                    break;
                case ENDS_WITH_LAYERED:
                    FileEndsWithLayered fileEndsWithLayered = new FileEndsWithLayered();
                    checks.add(fileEndsWithLayered.check(rule, file, files.getFileAndPath(file).getPath()));
                    break;
                default:
                    System.out.println("Todo");
            }
        }
        return checks;
    }
}
