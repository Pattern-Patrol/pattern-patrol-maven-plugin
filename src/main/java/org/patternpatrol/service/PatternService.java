package org.patternpatrol.service;

import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.FileAndPathList;

import java.util.List;

public interface PatternService<T, R> {

    List<CheckResult> validate(T rule, R pattern, FileAndPathList<String, String> files, List<CheckResult> existingChecks);
}
