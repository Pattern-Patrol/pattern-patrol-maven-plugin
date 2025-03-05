package org.patternpatrol.service.impl;

import org.patternpatrol.enums.DirectoryPattern;
import org.patternpatrol.model.CheckResult;
import org.patternpatrol.model.DirectoryRule;
import org.patternpatrol.rule.impl.PackageContains;
import org.patternpatrol.rule.impl.PackageDomainArchitecture;
import org.patternpatrol.rule.impl.PackageEndsWith;
import org.patternpatrol.rule.impl.PackageImplementation;
import org.patternpatrol.rule.impl.PackageLayeredArchitecture;
import org.patternpatrol.rule.impl.PackageStartsWith;
import org.patternpatrol.service.PatternService;

import java.util.List;
import java.util.Set;


public class DirectoryPatternService implements PatternService<DirectoryRule, DirectoryPattern> {

    @Override
    public List<CheckResult> validate(final DirectoryRule rule, final DirectoryPattern pattern, final Set<String> files, final List<CheckResult> existingChecks) {
        List<CheckResult> checks = existingChecks;
        for (String file : files) {
            switch (pattern) {
                case LAYERED:
                    PackageLayeredArchitecture packageLayeredArchitecture = new PackageLayeredArchitecture();
                    checks.add(packageLayeredArchitecture.check(rule, file));
                    break;
                case IMPLEMENTATION:
                    PackageImplementation packageImplementation = new PackageImplementation();
                    checks.add(packageImplementation.check(rule, file));
                    break;
                case DOMAIN_DRIVEN:
                    PackageDomainArchitecture packageDomainArchitecture = new PackageDomainArchitecture();
                    checks.add(packageDomainArchitecture.check(rule, file));
                    break;
                case ENDS_WITH:
                    PackageEndsWith packageEndsWith = new PackageEndsWith();
                    checks.add(packageEndsWith.check(rule, file));
                    break;
                case STARTS_WITH:
                    PackageStartsWith packageStartsWith = new PackageStartsWith();
                    checks.add(packageStartsWith.check(rule, file));
                    break;
                case CONTAINS:
                    PackageContains packageContains = new PackageContains();
                    checks.add(packageContains.check(rule, file));
                    break;
                default:
                    System.out.println("Todo");
            }
        }

        return checks;
    }
}
