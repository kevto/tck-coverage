package org.twinternet.tck;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class TckTestExecutionExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        // TODO
        System.out.println("Before test");
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        // TODO
        System.out.println("After test");
    }
}
