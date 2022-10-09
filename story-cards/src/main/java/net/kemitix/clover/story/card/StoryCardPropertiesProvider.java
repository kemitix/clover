package net.kemitix.clover.story.card;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class StoryCardPropertiesProvider {

    @Produces
    @ApplicationScoped
    StoryCardProperties cloverProperties() {
        Config config = ConfigProvider.getConfig();
        return new StoryCardProperties() {
            @Override
            public boolean enabled() {
                return config.getValue("clover.story-card.enabled", Boolean.class);
            }

            @Override
            public int padding() {
                return config.getValue("clover.story-card.padding", Integer.class);
            }

            @Override
            public int logoFontSize() {
                return config.getValue("clover.story-card.logo-font-size", Integer.class);
            }

            @Override
            public int width() {
                return config.getValue("clover.story-card.width", Integer.class);
            }

            @Override
            public int height() {
                return config.getValue("clover.story-card.height", Integer.class);
            }
        };
    }

}