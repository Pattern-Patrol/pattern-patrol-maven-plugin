package org.patternpatrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.patternpatrol.enums.FileNamingStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileRule extends LogLevelRule implements RuleType {
    private FileNamingStandard naming;
    @JsonProperty(required = false)
    private String namingArg;
    @JsonProperty(required = false)
    private Set<String> namingArgs;
    @JsonProperty(required = false)
    private Set<String> ignoreFiles;
}
