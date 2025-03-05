package org.patternpatrol.service;

import org.patternpatrol.exception.PatternPatrolException;
import org.patternpatrol.model.Config;

import java.io.File;

public interface ConfigValidationService {
    Config validateConfig(File configFile) throws PatternPatrolException;
}
