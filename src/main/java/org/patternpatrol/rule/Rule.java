package org.patternpatrol.rule;

import org.patternpatrol.model.CheckResult;

public interface Rule<RuleType> {
    CheckResult check(RuleType rule, String targetPath, String fullPath);
}
