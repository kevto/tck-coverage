package org.twinternet.tck;

import org.twinternet.tck.writer.DefaultFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class CoverageReportExecution {

    private static final String RESULTS_FILENAME = "target/test-results.txt";

    private static final Object LOCK = new Object();

    private static volatile CoverageReport report;

    private CoverageReportExecution() {
        // Prevent instantiation
    }

    static CoverageReport getCurrentReport() {
        if (Objects.isNull(report)) {
            synchronized (LOCK) {
                if (Objects.isNull(report)) {
                    report = createReportInstance();
                }
            }
        }
        return report;
    }

    static CoverageReport createNewReport() {
        synchronized (LOCK) {
            report = createReportInstance();
        }
        return report;
    }

    private static CoverageReport createReportInstance() {
        CoverageReport report;
        try {
            report = CoverageReport.of(DefaultFileWriter.of(new File(RESULTS_FILENAME)));
        } catch (IOException e) {
            try {
                report = CoverageReport.of(Util.getDefaultCoverageReportWriter());
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        }
        return report;
    }
}
