package org.twinternet.tck;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class CoverageReportExecution {

    private static final Object LOCK = new Object();
    private static volatile CoverageReport report;

    private CoverageReportExecution() {
        // Empty.
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
        final CoverageReport report;
        try {
            report = CoverageReport.of(Util.getDefaultCoverageReportWriter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return report;
    }
}
