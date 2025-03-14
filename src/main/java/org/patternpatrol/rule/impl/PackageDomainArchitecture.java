package org.patternpatrol.rule.impl;

import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;


public class PackageDomainArchitecture implements DirectoryPatternRule {

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        return null; //new CheckResult();
    }
}
