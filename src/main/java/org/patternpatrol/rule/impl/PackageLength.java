package org.patternpatrol.rule.impl;

import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PackageLength implements DirectoryPatternRule {
    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        throw new NotImplementedException();
    }
}
