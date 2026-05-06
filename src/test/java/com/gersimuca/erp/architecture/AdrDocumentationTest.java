package com.gersimuca.erp.architecture;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AdrDocumentationTest {

  private static final String ADR_DIRECTORY = "design/adr";
  private static final String ADR_011_FILENAME = "011-opentelemetry-distributed-observability.md";
  private static final String ADR_INDEX_FILENAME = "adr-index.md";
  private static final String ADR_011_ID = "011";

  private static Path adrDirectory;
  private static Path adr011Path;
  private static Path adrIndexPath;

  private static String adr011Content;
  private static String adrIndexContent;

  @BeforeAll
  static void setup() throws IOException {
    adrDirectory = Paths.get(ADR_DIRECTORY);
    adr011Path = adrDirectory.resolve(ADR_011_FILENAME);
    adrIndexPath = adrDirectory.resolve(ADR_INDEX_FILENAME);

    adr011Content = Files.readString(adr011Path);
    adrIndexContent = Files.readString(adrIndexPath);
  }

  // =========================================================
  // ADR 011 file existence and non-emptiness
  // =========================================================

  @Test
  void adr_011_file_should_exist() {
    assertThat(adr011Path).exists().isRegularFile();
  }

  @Test
  void adr_011_file_should_not_be_empty() {
    assertThat(adr011Content).isNotBlank();
  }

  @Test
  void adr_011_file_should_have_meaningful_content_size() {
    assertThat(adr011Content.length()).isGreaterThan(500);
  }

  // =========================================================
  // ADR 011 required top-level sections
  // =========================================================

  @ParameterizedTest(name = "adr_011_should_contain_required_section [{0}]")
  @ValueSource(
      strings = {
        "## Context",
        "## Decision",
        "## Consequences",
        "## Alternatives Considered",
        "## Future Considerations",
        "## References"
      })
  void adr_011_should_contain_required_section(String section) {
    assertThat(adr011Content).contains(section);
  }

  // =========================================================
  // ADR 011 title and identifier
  // =========================================================

  @Test
  void adr_011_should_have_correct_title_header() {
    assertThat(adr011Content).contains("# 011-opentelemetry-distributed-observability");
  }

  @Test
  void adr_011_title_should_appear_as_first_line() throws IOException {
    List<String> lines = Files.readAllLines(adr011Path);
    assertThat(lines).isNotEmpty();
    assertThat(lines.get(0)).startsWith("# 011-");
  }

  // =========================================================
  // ADR 011 Decision section content
  // =========================================================

  @Test
  void adr_011_decision_should_reference_opentelemetry() {
    assertThat(adr011Content).containsIgnoringCase("OpenTelemetry");
  }

  @Test
  void adr_011_decision_should_mention_otlp_protocol() {
    assertThat(adr011Content).containsIgnoringCase("OTLP");
  }

  @Test
  void adr_011_decision_should_describe_core_components() {
    assertThat(adr011Content).contains("### Core components");
  }

  @Test
  void adr_011_decision_should_describe_export_configuration() {
    assertThat(adr011Content).contains("### Export configuration");
  }

  @Test
  void adr_011_decision_should_describe_service_configuration() {
    assertThat(adr011Content).contains("### Service configuration");
  }

  @Test
  void adr_011_decision_should_describe_custom_instrumentation() {
    assertThat(adr011Content).contains("### Custom instrumentation");
  }

  @Test
  void adr_011_decision_should_reference_service_name() {
    assertThat(adr011Content).contains("erp-customer-manager");
  }

  @Test
  void adr_011_decision_should_reference_trace_aspect() {
    assertThat(adr011Content).contains("TraceAspect");
  }

  @Test
  void adr_011_decision_should_include_grpc_endpoint() {
    assertThat(adr011Content).contains("4317");
  }

  @Test
  void adr_011_decision_should_mention_enabled_signals() {
    List<String> expectedSignals = Arrays.asList("Traces", "Metrics", "Logs");
    for (String signal : expectedSignals) {
      assertThat(adr011Content).as("Expected signal '%s' to be present", signal).contains(signal);
    }
  }

  @Test
  void adr_011_decision_should_mention_propagation_standards() {
    assertThat(adr011Content).contains("tracecontext");
    assertThat(adr011Content).contains("baggage");
  }

  // =========================================================
  // ADR 011 Consequences section
  // =========================================================

  @Test
  void adr_011_consequences_should_have_positive_subsection() {
    assertThat(adr011Content).contains("### Positive");
  }

  @Test
  void adr_011_consequences_should_have_negative_subsection() {
    assertThat(adr011Content).contains("### Negative");
  }

  @Test
  void adr_011_consequences_positive_should_mention_distributed_tracing() {
    assertThat(adr011Content).containsIgnoringCase("distributed tracing");
  }

  @Test
  void adr_011_consequences_negative_should_mention_runtime_overhead() {
    assertThat(adr011Content).containsIgnoringCase("overhead");
  }

  // =========================================================
  // ADR 011 Alternatives Considered section
  // =========================================================

  @Test
  void adr_011_alternatives_should_list_rejected_logging_only_approach() {
    assertThat(adr011Content).contains("Logging-only");
  }

  @Test
  void adr_011_alternatives_should_list_rejected_zipkin() {
    assertThat(adr011Content).contains("Zipkin");
  }

  @Test
  void adr_011_alternatives_should_list_rejected_metrics_only() {
    assertThat(adr011Content).contains("Metrics-only");
  }

  @Test
  void adr_011_alternatives_should_list_rejected_commercial_apm() {
    assertThat(adr011Content).contains("Commercial APM");
  }

  @Test
  void adr_011_each_alternative_should_state_rejection_reason() {
    long rejectedCount =
        Stream.of("Rejected").filter(keyword -> adr011Content.contains(keyword)).count();
    assertThat(rejectedCount).isGreaterThanOrEqualTo(1);

    long rejectionLines =
        adr011Content
            .lines()
            .filter(line -> line.toLowerCase().contains("rejected"))
            .count();
    assertThat(rejectionLines).isGreaterThanOrEqualTo(4);
  }

  // =========================================================
  // ADR 011 References section
  // =========================================================

  @Test
  void adr_011_references_should_link_to_opentelemetry_official_site() {
    assertThat(adr011Content).contains("https://opentelemetry.io/");
  }

  @Test
  void adr_011_references_should_link_to_java_instrumentation_repository() {
    assertThat(adr011Content)
        .contains("https://github.com/open-telemetry/opentelemetry-java-instrumentation");
  }

  @Test
  void adr_011_references_should_link_to_java_sdk_repository() {
    assertThat(adr011Content).contains("https://github.com/open-telemetry/opentelemetry-java");
  }

  @Test
  void adr_011_references_section_should_have_at_least_two_links() {
    long linkCount = adr011Content.lines().filter(line -> line.contains("https://")).count();
    assertThat(linkCount).isGreaterThanOrEqualTo(2);
  }

  // =========================================================
  // ADR 011 Future Considerations section
  // =========================================================

  @Test
  void adr_011_future_considerations_should_mention_java_agent_evaluation() {
    assertThat(adr011Content).containsIgnoringCase("Java agent");
  }

  @Test
  void adr_011_future_considerations_should_mention_kubernetes_collector() {
    assertThat(adr011Content).containsIgnoringCase("Kubernetes");
  }

  @Test
  void adr_011_future_considerations_should_have_multiple_items() {
    long bulletPoints =
        adr011Content
            .lines()
            .filter(line -> line.startsWith("- "))
            .count();
    assertThat(bulletPoints).isGreaterThanOrEqualTo(10);
  }

  // =========================================================
  // ADR index file existence and integrity
  // =========================================================

  @Test
  void adr_index_file_should_exist() {
    assertThat(adrIndexPath).exists().isRegularFile();
  }

  @Test
  void adr_index_file_should_not_be_empty() {
    assertThat(adrIndexContent).isNotBlank();
  }

  // =========================================================
  // ADR index entry for ADR 011
  // =========================================================

  @Test
  void adr_index_should_contain_entry_for_adr_011() {
    assertThat(adrIndexContent).contains(ADR_011_ID);
  }

  @Test
  void adr_index_entry_for_011_should_have_accepted_status() {
    String adr011Row =
        adrIndexContent
            .lines()
            .filter(line -> line.contains("| 011 |"))
            .findFirst()
            .orElse("");
    assertThat(adr011Row).isNotBlank();
    assertThat(adr011Row).containsIgnoringCase("Accepted");
  }

  @Test
  void adr_index_entry_for_011_should_reference_correct_filename() {
    assertThat(adrIndexContent)
        .contains("011-opentelemetry-distributed-observability");
  }

  @Test
  void adr_index_entry_for_011_should_describe_opentelemetry_observability() {
    String adr011Row =
        adrIndexContent
            .lines()
            .filter(line -> line.contains("| 011 |"))
            .findFirst()
            .orElse("");
    assertThat(adr011Row).containsIgnoringCase("Opentelemetry");
  }

  @Test
  void adr_index_entry_for_011_should_contain_markdown_link() {
    assertThat(adrIndexContent)
        .contains("[011-opentelemetry-distributed-observability]");
  }

  @Test
  void adr_index_link_for_011_should_point_to_existing_file() {
    assertThat(adr011Path).exists();
  }

  // =========================================================
  // ADR index table structural integrity
  // =========================================================

  @Test
  void adr_index_should_have_table_header() {
    assertThat(adrIndexContent).contains("| ID  |");
    assertThat(adrIndexContent).contains("| Title");
    assertThat(adrIndexContent).contains("| Status");
    assertThat(adrIndexContent).contains("| Link");
  }

  @Test
  void adr_index_should_list_011_as_last_accepted_entry_before_separator() {
    List<String> lines = adrIndexContent.lines().toList();
    int adr011LineIndex = -1;
    int separatorLineIndex = -1;

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      if (line.contains("| 011 |")) {
        adr011LineIndex = i;
      }
      if (line.startsWith("---")) {
        separatorLineIndex = i;
      }
    }

    assertThat(adr011LineIndex).isGreaterThan(0);
    assertThat(separatorLineIndex).isGreaterThan(adr011LineIndex);
  }

  @Test
  void adr_index_should_contain_exactly_eleven_adr_entries() {
    long adrEntryCount =
        adrIndexContent
            .lines()
            .filter(line -> line.matches("\\| \\d{3} \\|.*"))
            .count();
    assertThat(adrEntryCount).isEqualTo(11);
  }

  @Test
  void adr_index_all_status_values_should_be_valid() {
    List<String> validStatuses = Arrays.asList("Accepted", "Pending", "Deprecated");
    adrIndexContent
        .lines()
        .filter(line -> line.matches("\\| \\d{3} \\|.*"))
        .forEach(
            row ->
                assertThat(validStatuses)
                    .as("Row '%s' should have a valid status", row)
                    .anyMatch(row::contains));
  }

  // =========================================================
  // ADR directory structural integrity
  // =========================================================

  @Test
  void adr_directory_should_exist() {
    assertThat(adrDirectory).exists().isDirectory();
  }

  @Test
  void adr_directory_should_contain_adr_011_file() throws IOException {
    try (Stream<Path> files = Files.list(adrDirectory)) {
      assertThat(files.map(Path::getFileName).map(Path::toString))
          .contains(ADR_011_FILENAME);
    }
  }

  @Test
  void adr_directory_should_contain_index_file() throws IOException {
    try (Stream<Path> files = Files.list(adrDirectory)) {
      assertThat(files.map(Path::getFileName).map(Path::toString))
          .contains(ADR_INDEX_FILENAME);
    }
  }

  // =========================================================
  // Regression: ADR 011 content ordering
  // =========================================================

  @Test
  void adr_011_sections_should_appear_in_correct_order() {
    int contextIndex = adr011Content.indexOf("## Context");
    int decisionIndex = adr011Content.indexOf("## Decision");
    int consequencesIndex = adr011Content.indexOf("## Consequences");
    int alternativesIndex = adr011Content.indexOf("## Alternatives Considered");
    int futureIndex = adr011Content.indexOf("## Future Considerations");
    int referencesIndex = adr011Content.indexOf("## References");

    assertThat(contextIndex).isGreaterThan(-1);
    assertThat(decisionIndex).isGreaterThan(contextIndex);
    assertThat(consequencesIndex).isGreaterThan(decisionIndex);
    assertThat(alternativesIndex).isGreaterThan(consequencesIndex);
    assertThat(futureIndex).isGreaterThan(alternativesIndex);
    assertThat(referencesIndex).isGreaterThan(futureIndex);
  }

  @Test
  void adr_011_context_section_should_describe_the_problem_space() {
    int contextStart = adr011Content.indexOf("## Context");
    int decisionStart = adr011Content.indexOf("## Decision");
    String contextSection = adr011Content.substring(contextStart, decisionStart);
    assertThat(contextSection).containsIgnoringCase("tracing");
    assertThat(contextSection).containsIgnoringCase("observability");
  }
}
