package net.kemitix.clover.service;

import lombok.Getter;

import java.io.File;

public class ConfigLoadException
        extends RuntimeException {

    @Getter
    private final Class<?> theRoot;
    @Getter
    private final File file;

    public ConfigLoadException(
            String messageTemplate,
            Class<?> theRoot,
            File file
    ) {
        super(messageTemplate.formatted(theRoot.getName(), file));
        this.theRoot = theRoot;
        this.file = file;
    }

    public ConfigLoadException(
            String messageTemplate,
            Class<?> theRoot,
            File file,
            Exception cause
    ) {
        super(messageTemplate.formatted(theRoot.getName(), file), cause);
        this.theRoot = theRoot;
        this.file = file;
    }
}
