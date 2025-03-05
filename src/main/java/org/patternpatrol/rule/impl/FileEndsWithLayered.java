package org.patternpatrol.rule.impl;

import org.patternpatrol.constant.LayeredConstants;
import org.patternpatrol.helper.TextCheckHelper;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.FileRule;
import org.patternpatrol.rule.FileNamingStandardRule;


public class FileEndsWithLayered implements FileNamingStandardRule {

    @Override
    public CheckResult check(final FileRule fileRule, final String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(LayeredConstants.getFileNames());
        textCheckHelper.setIgnore(fileRule.getIgnoreFiles());
        textCheckHelper.setLogLevel(fileRule.getLevel());
        return textCheckHelper.endsWith();
    }
}
