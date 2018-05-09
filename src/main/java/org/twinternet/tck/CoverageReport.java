package org.twinternet.tck;

import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class CoverageReport {

    private static final String PROPERTIES_FILE = "tck-coverage.properties";

    private final CoverageReportWriter writer;

    private final CoverageReportContext context;

    private final Properties jsrProperties;

    private CoverageReport(final CoverageReportWriter writer, final CoverageReportContext context, final Properties properties) {
        this.writer = writer;
        this.context = context;
        this.jsrProperties = properties;
    }

    public static CoverageReport of(final CoverageReportWriter writer, final CoverageReportContext context) throws IOException {
        Objects.requireNonNull(writer);
        Objects.requireNonNull(context);

        final Properties properties = new Properties();
        final File propertiesFile = Util.getFileFromResources(PROPERTIES_FILE);
        if (Objects.isNull(propertiesFile)) {
            throw new FileNotFoundException("Could not find properties file in resources: " + PROPERTIES_FILE);
        }
        final InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        inputStream.close();

        return new CoverageReport(writer, context, properties);
    }

    public static CoverageReport of(final CoverageReportWriter writer) throws IOException {
        return of(writer, CoverageReportContext.of());
    }

}
