package net.kemitix.clover.spi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IssueType {

    ISSUE("Issue"),
    YEAR("Year");

    final private String value;

    @Override
    public String toString() {
        return value;
    }
}
