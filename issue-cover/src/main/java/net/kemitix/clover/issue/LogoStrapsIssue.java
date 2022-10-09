package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import net.kemitix.clover.spi.AbstractLogoStraps;
import net.kemitix.clover.spi.FrontCover;

@FrontCover
@ApplicationScoped
public class LogoStrapsIssue extends AbstractLogoStraps
        implements ForIssueCovers {

}
