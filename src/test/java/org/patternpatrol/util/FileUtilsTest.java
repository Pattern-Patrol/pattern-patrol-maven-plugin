package org.patternpatrol.util;

import org.patternpatrol.model.Config;
import org.junit.Test;
import org.patternpatrol.model.FileAndPathList;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

    @Test
    public void testShouldListAllPathsInAGivenBase() throws IOException {
        //Given
        Config config = new Config();
        config.setBasePackage("org.patternpatrol");

        //When
        FileAndPathList<String, String> results = FileUtils.getAllPackagesAtBase(config);
        //Then
        assertTrue(results.size() > 1);
    }
}
