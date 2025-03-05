package org.patternpatrol.service;

import org.patternpatrol.enums.LogLevel;
import org.patternpatrol.exception.ResultsException;
import org.patternpatrol.model.CheckResult;
import org.apache.maven.plugin.logging.Log;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsService {

    public void checkResults(final List<CheckResult> results, final LogLevel failOn, final Log log) throws ResultsException {
        List<CheckResult> failures = results.stream().filter(line -> !line.isSuccess()).collect(Collectors.toList());
        log.info("There are " + failures.size() + " errors reported by Pattern Patrol");
        failures.forEach(line -> {
            switch (line.getLogLevel()) {
                case DEBUG:
                    log.debug(line.getErrorMessage());
                    break;
                case INFO:
                    log.info(line.getErrorMessage());
                    break;
                case WARN:
                    log.warn(line.getErrorMessage());
                    break;
                case ERROR:
                    log.error(line.getErrorMessage());
                    break;
                case FATAL:
                    log.error(line.getErrorMessage());
                default:
                    log.error(line.getErrorMessage());
            }
        });
        if (!failures.isEmpty()) {
            throw new ResultsException("There are " + failures.size() + " errors reported by Pattern Patrol");
        }
    }
}
