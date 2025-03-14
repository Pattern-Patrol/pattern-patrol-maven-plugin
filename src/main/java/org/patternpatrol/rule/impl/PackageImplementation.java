package org.patternpatrol.rule.impl;

import org.patternpatrol.helper.TextCheckHelper;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PackageImplementation implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("impl", "implementation")));

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        // Create list of allowed module names from defaults and args
        Set<String> allowedModuleNames = ALLOWED_MODULE_NAMES;
        if (directoryRule.getPatternArgs() != null) {
            allowedModuleNames.addAll(directoryRule.getPatternArgs());
        }
        if (directoryRule.getPatternArg() != null) {
            allowedModuleNames.add(directoryRule.getPatternArg());
        }

        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(allowedModuleNames);
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        textCheckHelper.setLogLevel(directoryRule.getLevel());
        textCheckHelper.setRuleType(directoryRule.getPattern().name());
        return textCheckHelper.contains();
    }
}
