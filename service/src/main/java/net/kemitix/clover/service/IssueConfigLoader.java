package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.ImageLoader;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.files.FileReader;
import net.kemitix.files.FileReaderWriter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.bind.Jsonb;
import java.io.File;
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
    public FileReader fileReader() {
        return new FileReaderWriter();
    }

    @Produces
    @ApplicationScoped
    public IssueConfig loadIssue(
            final CloverProperties config,
            final FileReader fileReader
    ) {
        final File cloverFile =
                Paths.get(config.getIssueDir(), config.getConfigFile())
                .toFile();
        LOGGER.info("Reading: " + cloverFile.getAbsolutePath());
        return parseYamlFromFile(cloverFile, ServiceIssueConfig.class, fileReader);
    }

    private <T> T parseYamlFromFile(
            final File file,
            final Class<T> theRoot,
            final FileReader fileReader
    ) {
        try {
            Yaml yaml = new Yaml(new Constructor(theRoot));
            T loaded = yaml.load(fileReader.read(file));
            if (loaded == null) {
                throw new RuntimeException("File not compatible with %%s: %s%s"
                        .formatted(theRoot.getName(), file));
            }
            return loaded;
        } catch (YAMLException | IOException e) {
            throw new RuntimeException("Error reading a %s from file %s"
                    .formatted(theRoot.getName(), file), e);
        }

    }

    @Produces
    @ApplicationScoped
    public Image coverArtImage(
            final CloverProperties cloverProperties,
            final IssueConfig issueConfig,
            final ImageLoader imageLoader
    ) throws IOException {
        final Path coverArtPath = Paths.get(
                cloverProperties.getIssueDir(),
                issueConfig.getCoverArt());
        return imageLoader.load(coverArtPath.toFile());
    }

}
