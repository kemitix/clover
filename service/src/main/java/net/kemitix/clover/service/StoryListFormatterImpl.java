package net.kemitix.clover.service;

import net.kemitix.clover.spi.IssueStory;
import net.kemitix.clover.spi.StoryListFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class StoryListFormatterImpl implements StoryListFormatter {

    public List<String> format(
            final String label,
            final List<? extends IssueStory> stories
    ) {
        final List<String> list = new ArrayList<>();
        list.add(label);
        list.addAll(storyStream(stories)
                .flatMap(story -> Stream.of(
                        "",
                        story.getTitle(),
                        "by " + story.getAuthor().authorName()))
                .flatMap(this::splitOnLineBreaks)
                .map(String::trim)
                .collect(Collectors.toList()));
        return list;
    }

    private Stream<? extends IssueStory> storyStream(List<? extends IssueStory> stories) {
        return Optional.ofNullable(stories).map(Collection::stream).orElseGet(Stream::empty);
    }

    private Stream<String> splitOnLineBreaks(final String line) {
        return Arrays.stream(line.split("\n"));
    }
}
