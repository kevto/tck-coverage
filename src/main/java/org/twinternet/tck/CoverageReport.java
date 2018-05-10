package org.twinternet.tck;

import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

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
}
