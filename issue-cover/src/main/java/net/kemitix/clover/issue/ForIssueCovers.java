package net.kemitix.clover.issue;

import net.kemitix.clover.spi.ForIssueTypeFilter;
import net.kemitix.clover.spi.IssueType;

public interface ForIssueCovers
        extends ForIssueTypeFilter {

    @Override
    default boolean typeFilter(IssueType type) {
        return IssueType.ISSUE.equals(type);
    }

}
