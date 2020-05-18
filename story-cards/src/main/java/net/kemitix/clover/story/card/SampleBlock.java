package net.kemitix.clover.story.card;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@StoryCard
@ApplicationScoped
public class SampleBlock implements Element<Graphics2D> {

    @Getter private final int priority = 40;

    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;
    @Inject @Named("alice") FontFace aliceFontFace;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        IssueStory story =
                typedProperties.find(TypedKeys.Story.class, IssueStory.class)
                        .orElseThrow();
        FontFace fontFace = aliceFontFace;
        var text = story.getSample() + "\n" +
                "Organize content to meet your goals\n" +
                "\n" +
                "Channels are an intuitive way to organize and share Pluralsight content so you can reach your learning goals and business objectives more effectively. Create channels to curate content for your own learning, for team development or to share learning journeys with the world. \n" +
                "Organize content to meet your goals\n" +
                "\n" +
                "Channels are an intuitive way to organize and share Pluralsight content so you can reach your learning goals and business objectives more effectively. Create channels to curate content for your own learning, for team development or to share learning journeys with the world. \n" +
                "Organize content to meet your goals\n" +
                "\n" +
                "Channels are an intuitive way to organize and share Pluralsight content so you can reach your learning goals and business objectives more effectively. Create channels to curate content for your own learning, for team development or to share learning journeys with the world.";
        Region region = dimensions.getSampleRegion();
        opaqueFill
                .opacity(0.7D)
                .colour("gray")
                .region(region)
                .accept(drawable);
        simpleTextEffect
                .fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region)
                .accept(drawable);

//        drawable.drawRect(region.getLeft(), region.getTop(),
//                region.getWidth(), region.getHeight());
    }
}
