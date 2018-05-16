package org.twinternet.tck;

import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.io.PrintStream;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class TckCoverageExecutionListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        CoverageReportExecution.createNewReport();
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        CoverageReportExecution.getCurrentReport().write();
    }


}
