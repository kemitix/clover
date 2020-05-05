package net.kemitix.clover.service;


import net.kemitix.clover.spi.CloverConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class IssueLoader {

    private static final Logger LOGGER =
            Logger.getLogger(
                    IssueLoader.class.getName());

    @Produces
    @ApplicationScoped
    public Issue loadIssueJson(
            final CloverConfig config,
            final Jsonb jsonb
    ) throws FileNotFoundException {
        final Path cloverJsonPath = Paths.get(config.getIssueDir(), config.getConfigFile());
        LOGGER.info("Reading: " + cloverJsonPath);
        final FileReader reader = new FileReader(cloverJsonPath.toFile());
        return jsonb.fromJson(reader, Issue.class);
    }

}
