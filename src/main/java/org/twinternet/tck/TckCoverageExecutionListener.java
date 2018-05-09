package org.twinternet.tck;

import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class TckCoverageExecutionListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        // TODO
        System.out.println("Testplan Execution started");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        // TODO
        System.out.println("Testplan Execution ended");
    }
}
