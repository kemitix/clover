package net.kemitix.clover.issue;

import net.kemitix.clover.spi.AuthorStrapBox;
import net.kemitix.clover.spi.IssueConfig;

import javax.enterprise.inject.Produces;

public class AuthorListProducers {

    @Produces
    AuthorStrapBox authorStrapBox(IssueConfig issueConfig) {
        return issueConfig.getAuthorStrapBox();
    }

}
