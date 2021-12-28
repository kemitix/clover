package net.kemitix.clover.issue;

import net.kemitix.clover.spi.AbstractLogoStraps;
import net.kemitix.clover.spi.FrontCover;

import javax.enterprise.context.ApplicationScoped;

@FrontCover
@ApplicationScoped
public class LogoStrapsIssue extends AbstractLogoStraps
        implements ForIssueCovers {

}
