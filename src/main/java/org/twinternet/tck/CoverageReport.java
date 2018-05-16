package org.twinternet.tck;

import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class CoverageReport {

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
        final List<TckTestResult> failedResults = context.getTestResults().stream()
                .sorted(TestResultSorter::compareResults)
                .filter(t -> t.getThrowableType() != TckTestResult.ThrowableType.SUCCESS)
                .collect(Collectors.toList());

        // (Total results - failed results) * 100 / total results
        final Float successRate = ((context.getTestResults().size() - failedResults.size()) * 100.0f) / context.getTestResults().size();
        writer.writeGlobalResults(successRate, failedResults);
    }
}
