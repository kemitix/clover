package net.kemitix.clover.spi;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public interface IssueStories {
    List<? extends IssueStory> getSf();

    List<? extends IssueStory> getFantasy();

    List<? extends IssueStory> getScienceFantasy();

    List<? extends IssueStory> getReprintSf();

    List<? extends IssueStory> getReprintFantasy();

    List<? extends IssueStory> getReprintScienceFantasy();

    default Stream<IssueStory> stream() {
        return Stream.of(getSf(),
                        getFantasy(),
                        getScienceFantasy(),
                        getReprintSf(),
                        getReprintFantasy(),
                        getReprintScienceFantasy())
                .filter(Objects::nonNull)
                .flatMap(Collection::stream);
    }

}
