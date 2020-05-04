package net.kemitix.clover.service;

import net.kemitix.clover.spi.images.Image;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Kindle implements CloverFormat {

    private Paperback paperback;

    public Kindle() {
    }

    @Inject
    protected Kindle(final Paperback paperback) {
        this.paperback = paperback;
    }

    @Override
    public Image getImage() {
        System.out.println("Kindle.getImage");
        final Image kindle =
                paperback.getImage()
                        .crop(paperback.getFrontCoverCropRegion());
        System.out.println("Kindle.getImage - done");
        return kindle;
    }

    @Override
    public String getName() {
        return "kindle";
    }

    @Override
    public TypedProperties getImageProperties() {
        return TypedProperties.create();
    }
}
