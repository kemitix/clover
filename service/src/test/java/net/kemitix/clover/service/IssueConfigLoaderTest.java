package net.kemitix.clover.service;

public class IssueConfigLoaderTest {

//    private final ServiceCloverProperties cloverConfig =
//            new ServiceCloverProperties();
//    private final String issueNumber = UUID.randomUUID().toString();
//    private final Jsonb jsonb = JsonbBuilder.create();
//    private final IssueConfigLoader issueConfigLoader =
//            new IssueConfigLoader(cloverConfig, jsonb);
//    @TempDir
//    Path directory;
//
//    @Test
//    @DisplayName("Loads the clover.json file")
//    public void loadIssueJson() throws IOException {
//        //given
//        final String content =
//                String.format("{\"issue\":\"%s\"}}", issueNumber);
//        new FileReaderWriter()
//                .write(
//                        directory.resolve("clover.json").toFile(),
//                        content);
//        cloverConfig.issueDir = directory.toString();
//        cloverConfig.configFile = "clover.json";
//        //when
//        final Issue issue = issueConfigLoader.loadIssueJson();
//        //then
//        assertThat(issue.getIssue()).isEqualTo(issueNumber);
//    }
//
//    @Test
//    @DisplayName("When clover.json is missing throws an exception")
//    public void whenMissingThenThrow() {
//        cloverConfig.issueDir = directory.toString();
//        cloverConfig.configFile = "clover.json";
//        //then
//        assertThatExceptionOfType(IOException.class)
//                .isThrownBy(issueConfigLoader::loadIssueJson);
//    }
}