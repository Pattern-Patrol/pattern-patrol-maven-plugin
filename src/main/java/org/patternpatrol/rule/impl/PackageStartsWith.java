package org.patternpatrol.rule.impl;

import org.patternpatrol.helper.TextCheckHelper;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;

public class PackageStartsWith implements DirectoryPatternRule {
    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArg(directoryRule.getPatternArg());
        textCheckHelper.setArgs(directoryRule.getPatternArgs());
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        textCheckHelper.setLogLevel(directoryRule.getLevel());
        textCheckHelper.setRuleType(directoryRule.getPattern().name());
        return textCheckHelper.endsWith();
    }
}
