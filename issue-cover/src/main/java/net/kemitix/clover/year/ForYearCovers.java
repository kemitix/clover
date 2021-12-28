package net.kemitix.clover.year;

import net.kemitix.clover.spi.ForIssueTypeFilter;
import net.kemitix.clover.spi.IssueType;

public interface ForYearCovers extends ForIssueTypeFilter {

    @Override
    default boolean typeFilter(IssueType type) {
        return IssueType.YEAR.equals(type);
    }

}
