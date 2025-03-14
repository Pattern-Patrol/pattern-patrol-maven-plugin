package org.patternpatrol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileAndPath<T, U> {
    private T file;
    private U path;
}
