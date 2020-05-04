package net.kemitix.clover.service;


import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.ImageService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.bind.Jsonb;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class IssueConfigLoader {

    private static final Logger LOGGER =
            Logger.getLogger(
                    IssueConfigLoader.class.getName());

    @Produces
    @ApplicationScoped
    public IssueConfig loadIssueJson(
            final CloverProperties cloverProperties,
            final Jsonb jsonb
    ) throws FileNotFoundException {
        final Path cloverJsonPath =
                Paths.get(cloverProperties.getIssueDir(),
                        cloverProperties.getConfigFile());
        LOGGER.info("Reading: " + cloverJsonPath);
        final FileReader reader = new FileReader(cloverJsonPath.toFile());
        return jsonb.fromJson(reader, IssueConfig.class);
    }

    @Produces
    @ApplicationScoped
    public Image coverArtImage(
            final CloverProperties cloverProperties,
            final IssueConfig issueConfig,
            final ImageService imageService
    ) throws IOException {
        final Path coverArtPath = Paths.get(
                cloverProperties.getIssueDir(),
                issueConfig.getCoverArt());
        return imageService.load(coverArtPath.toFile());
    }

}
