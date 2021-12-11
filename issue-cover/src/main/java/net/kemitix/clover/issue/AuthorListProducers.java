package net.kemitix.clover.issue;

import jakarta.enterprise.inject.Produces;
import net.kemitix.clover.spi.AuthorStrapBox;
import net.kemitix.clover.spi.IssueConfig;

public class AuthorListProducers {

    @Produces
    AuthorStrapBox authorStrapBox(IssueConfig issueConfig) {
        return issueConfig.getAuthorStrapBox();
    }

}
