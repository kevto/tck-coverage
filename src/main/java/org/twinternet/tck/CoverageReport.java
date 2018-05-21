package org.twinternet.tck;

import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class CoverageReport {

    private static final String SECTIONLESS_RESULTS_HEADER = "[SR] Sectionless Results";
    
    private static final String PROPERTIES_FILE = "tck-coverage.properties";

    private final CoverageReportWriter writer;

    private final CoverageReportContext context;

    private final CoverageReportProperties jsrProperties;

    private CoverageReport(final CoverageReportWriter writer, final CoverageReportContext context, final CoverageReportProperties properties) {
        this.writer = writer;
        this.context = context;
        this.jsrProperties = properties;
    }

    static CoverageReport of(final CoverageReportWriter writer, final CoverageReportContext context) throws IOException {
        Objects.requireNonNull(writer);
        Objects.requireNonNull(context);

        final File propertiesFile = Util.getFileFromResources(PROPERTIES_FILE);
        if (Objects.isNull(propertiesFile)) {
            throw new FileNotFoundException("Could not find properties file in resources: " + PROPERTIES_FILE);
        }
        final CoverageReportProperties properties = CoverageReportProperties.of(propertiesFile);

        return new CoverageReport(writer, context, properties);
    }

    static CoverageReport of(final CoverageReportWriter writer) throws IOException {
        return of(writer, CoverageReportContext.of());
    }

    CoverageReportContext getContext() {
        return context;
    }

    synchronized void write() {
        // Metadata / header
        writer.writeMetadata(jsrProperties);
        final List<TckTestResult> sortedResults = context.getTestResults().stream()
                .sorted(Sorters::sortTestResults)
                .collect(Collectors.toList());
        
        // Filter failted results.
        final List<TckTestResult> failedResults = sortedResults.stream()
                .filter(t -> t.getThrowableType() != TckTestResult.ThrowableType.SUCCESS)
                .collect(CollectorsExt.toLinkedList());

        // (Total results - failed results) * 100 / total results
        final Float successRate = ((context.getTestResults().size() - failedResults.size()) * 100.0f) / context.getTestResults().size();
        writer.writeGlobalResults(successRate, failedResults);
        
        // Sort section on alphabet.
        final List<Section> sortedSections = context.getSections().stream()
                .sorted(Sorters::sortSections)
                .collect(Collectors.toList());
        
        // Sort results into the section.
        final List<SectionResults> sectionResultsContainer = new LinkedList<>();
        sortedSections.forEach((s) -> {
            final List<TckTestResult> sectionResults = sortedResults.stream()
                    .filter(r -> r.getSectionId().equals(s.id().value()))
                    .collect(CollectorsExt.toLinkedList());
            sectionResultsContainer.add(SectionResults.of(s, sectionResults));
        });
        writer.writeSectionResults(sectionResultsContainer);
        
        // Sort the left over results which don't fit into a section
        // also on alphabet.
        final List<TckTestResult> leftOverResults = new LinkedList<>();
        sortedResults.stream()
                .filter(r -> !context.isSectionRegistered(r.getSectionId()))
                .collect(CollectorsExt.toLinkedList());
        if (!leftOverResults.isEmpty()) {
            writer.writeResults(SECTIONLESS_RESULTS_HEADER, leftOverResults);
        }
    }
}
