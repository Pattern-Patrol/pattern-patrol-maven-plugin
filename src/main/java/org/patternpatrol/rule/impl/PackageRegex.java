package org.patternpatrol.rule.impl;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import org.patternpatrol.helper.TextCheckHelper;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.DirectoryPatternRule;

public class PackageRegex implements DirectoryPatternRule {
    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath, final String fullPath) {
        throw new NotImplementedException();
    }
}
