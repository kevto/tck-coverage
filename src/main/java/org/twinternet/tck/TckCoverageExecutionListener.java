package org.twinternet.tck;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class TckCoverageExecutionListener implements TestExecutionListener {

    private static final Object LOCK = new Object();
    private static volatile CoverageReport report;

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        getReport();
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        // TODO
        System.out.println("Testplan Execution ended");
    }

    static CoverageReport getReport() {
        if (Objects.isNull(report)) {
            synchronized (LOCK) {
                if (Objects.isNull(report)) {
                    report = createReportInstance();
                }
            }
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
