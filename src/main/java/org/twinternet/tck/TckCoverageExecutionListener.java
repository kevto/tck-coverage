package org.twinternet.tck;

import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class TckCoverageExecutionListener implements TestExecutionListener {



    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        // TODO
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        // TODO
        //final CoverageReport report = CoverageReportExecution.getCurrentReport();
        System.out.println("Testplan Execution ended");
    }


}
