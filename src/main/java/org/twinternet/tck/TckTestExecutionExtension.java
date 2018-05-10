package org.twinternet.tck;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.twinternet.tck.TckCoverageExecutionListener.getReport;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class TckTestExecutionExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        // TODO
        System.out.println(extensionContext.getDisplayName() + " before test");
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        // TODO
        final Optional<Throwable> throwable = extensionContext.getExecutionException();
        if (throwable.isPresent()) {
            System.out.println(extensionContext.getDisplayName() + " after test: " + throwable.get().getMessage());
        } else {
            System.out.println(extensionContext.getDisplayName() + " after test");
        }
    }
}
