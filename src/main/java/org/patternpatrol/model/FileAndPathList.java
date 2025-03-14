package org.patternpatrol.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileAndPathList<T, U> extends ArrayList<FileAndPath<T, U>> {

    public List<T> getFiles() {
        return this.stream()
                .map(FileAndPath::getFile)
                .collect(Collectors.toList());
    }

    public List<U> getPaths() {
        return this.stream()
                .map(FileAndPath::getPath)
                .collect(Collectors.toList());
    }

    public FileAndPath<T, U> getFileAndPath(final T file) {
        return this.stream()
                .filter(fap -> fap.getFile().equals(file))
                .findFirst()
                .orElse(null);
    }
}
