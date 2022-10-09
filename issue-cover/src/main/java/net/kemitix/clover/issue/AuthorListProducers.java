package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import net.kemitix.clover.spi.AuthorStrapBox;
import net.kemitix.clover.spi.IssueConfig;

@ApplicationScoped
public class AuthorListProducers {

    @Produces
    AuthorStrapBox authorStrapBox(IssueConfig issueConfig) {
        return issueConfig.getAuthorStrapBox();
    }

}
