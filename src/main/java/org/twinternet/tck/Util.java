package org.twinternet.tck;

import org.twinternet.tck.writer.ConsoleWriter;
import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class Util {

    private Util() {
        // Empty.
    }

    static CoverageReportWriter getDefaultCoverageReportWriter() {
        return ConsoleWriter.of();
    }

    public static File getFileFromResources(final String filename) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
        if (Objects.isNull(url)) {
            return null;
        }

        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            System.err.println("URISyntaxException occurred while trying to get a file from resources: " + e.toString());
            file = null;
        }

        if (Objects.nonNull(file) && file.exists()) {
            return file;
        }
        return null;
    }
}
