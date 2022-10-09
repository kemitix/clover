package net.kemitix.clover;

import net.kemitix.clover.story.card.StoryCardProperties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class ConfigLogging {

    private static final Logger LOGGER =
            Logger.getLogger(
                    ConfigLogging.class.getName());

    @Inject StoryCardProperties properties;

    @PostConstruct
    public void init() {
        LOGGER.info(String.format("story-card enabled: %b", properties.enabled()));
        LOGGER.info(String.format("story-card width: %d", properties.width()));
        LOGGER.info(String.format("story-card height: %d", properties.height()));
        LOGGER.info(String.format("story-card padding: %d", properties.padding()));
        LOGGER.info(String.format("story-card font size: %d", properties.logoFontSize()));
    }

}
