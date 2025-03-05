package org.patternpatrol.service;

import org.patternpatrol.model.CheckResult;

import java.util.List;
import java.util.Set;

public interface PatternService<T, R> {

    List<CheckResult> validate(T rule, R pattern, Set<String> files, List<CheckResult> existingChecks);
}
