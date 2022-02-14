package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.files.FileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class IssueConfigLoaderTest {

    private final CloverProperties cloverConfig;
    private final String issueNumber = UUID.randomUUID().toString();
    private final IssueConfigLoader issueLoader = new IssueConfigLoader();
    private final FileReader fileReader;

    public IssueConfigLoaderTest(
            @Mock CloverProperties cloverConfig,
            @Mock FileReader fileReader
    ) {
        this.cloverConfig = cloverConfig;
        this.fileReader = fileReader;
    }

    @Test
    @DisplayName("Loads the clover.yaml file")
    public void loadIssueYaml() throws IOException {
        //given
        given(cloverConfig.issueDir()).willReturn("dir");
        given(cloverConfig.configFile()).willReturn("clover.yaml");
        final String content =
                """
                        ---
                        type: ISSUE
                        issue: %s
                        titleColour: red
                        titleShadow: blue
                        subTitleColour: yellow
                        subTitleShadow: green
                        width: 5
                        height: 8
                        """.formatted(issueNumber);
        given(fileReader.read(any())).willReturn(content);
        //when
        final IssueConfig issue =
                issueLoader.loadIssue(cloverConfig, fileReader);
        //then
        assertThat(issue.getIssue()).isEqualTo(issueNumber);
        assertThat(issue.getTitleColour()).isEqualTo("red");
        assertThat(issue.getTitleShadow()).isEqualTo("blue");
        assertThat(issue.getSubTitleColour()).isEqualTo("yellow");
        assertThat(issue.getSubTitleShadow()).isEqualTo("green");
    }
}
