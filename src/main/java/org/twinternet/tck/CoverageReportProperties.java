package org.twinternet.tck;

import java.io.*;
import java.util.Properties;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class CoverageReportProperties {

    private static final String JSR_ID_KEY              = "jsr.id";
    private static final String JSR_VERSION_KEY         = "jsr.version";
    private static final String JSR_DESC_KEY            = "jsr.description";
    private static final String JSR_URL_KEY             = "jsr.url";
    private static final String JSR_COPYRIGHT_KEY       = "jsr.copyright";
    private static final String JSR_LICENSE_TYPE_KEY    = "jsr.license.type";
    private static final String JSR_LICENSE_URL_KEY     = "jsr.license.url";
    private static final String DEFAULT_VALUE           = "";


    private final Properties properties;

    private CoverageReportProperties(final Properties properties) {
        this.properties = properties;
    }

    /**
     * Properties wrapper for the coverage report tool. This class contains all the properties stored
     * in the tck-coverage.properties file. This factory method also validates the presence of the keys
     * in the .properties file.
     * @param propertiesFile The {@link File} properties file.
     * @return {@link CoverageReportProperties}
     * @throws IOException If the properties of the file could be read into the {@link Properties} object.
     * @throws IllegalArgumentException If one of the keys are missing, then the this exception will be thrown
     */
    static CoverageReportProperties of(final File propertiesFile) throws IOException {
        final Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFile)) {
            properties.load(inputStream);
        }
        validatePresenceOfKeys(properties);
        return new CoverageReportProperties(properties);
    }

    private static void validatePresenceOfKeys(final Properties properties) {
        final String[] propertyKeys = new String[]{JSR_ID_KEY, JSR_VERSION_KEY, JSR_DESC_KEY,
                JSR_URL_KEY, JSR_COPYRIGHT_KEY, JSR_LICENSE_TYPE_KEY, JSR_LICENSE_URL_KEY};
        for (String key : propertyKeys) {
            if (!properties.containsKey(key)) {
                throw new IllegalArgumentException("Missing key in properties file: " + key);
            }
        }
    }

    String getJsrId() {
        return properties.getProperty(JSR_ID_KEY, DEFAULT_VALUE);
    }

    String getJsrDescription() {
        return properties.getProperty(JSR_DESC_KEY, DEFAULT_VALUE);
    }

    String getJsrVersion() {
        return properties.getProperty(JSR_VERSION_KEY, DEFAULT_VALUE);
    }

    String getJsrUrl() {
        return properties.getProperty(JSR_URL_KEY, DEFAULT_VALUE);
    }

    String getJsrCopyright() {
        return properties.getProperty(JSR_COPYRIGHT_KEY, DEFAULT_VALUE);
    }

    String getJsrLicenseType() {
        return properties.getProperty(JSR_LICENSE_TYPE_KEY, DEFAULT_VALUE);
    }

    String getJsrLicenseUrl() {
        return properties.getProperty(JSR_LICENSE_URL_KEY, DEFAULT_VALUE);
    }

}
