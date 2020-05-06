package net.kemitix.clover.service;

import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Kindle implements CloverFormat {

    private Dimensions dimensions;
    private Paperback paperback;

    public Kindle() {
    }

    @Inject
    protected Kindle(
            Dimensions dimensions,
            final Paperback paperback
    ) {
        this.dimensions = dimensions;
        this.paperback = paperback;
    }

    @Override
    public Image getImage() {
        System.out.println("Kindle.getImage");
        Image paperbackImage = paperback.getImage();
        System.out.println("paperbackImage = " + paperbackImage.getRegion());
        Region frontCrop = dimensions.getFrontWithinWrapCrop();
        System.out.println("frontCrop = " + frontCrop);
        final Image kindle = paperbackImage.crop(frontCrop);
        System.out.println("kindle.getRegion() = " + kindle.getRegion());
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
