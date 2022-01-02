package net.kemitix.clover.spi;

public interface ForIssueTypeFilter {
    default boolean typeFilter(IssueType type) {
        return true;
    }
}
