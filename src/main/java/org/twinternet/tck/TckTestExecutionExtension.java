package org.twinternet.tck;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class TckTestExecutionExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final CoverageReportContext context = CoverageReportExecution.getCurrentReport().getContext();

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        final Class<?> testClass = extensionContext.getRequiredTestClass();
        if (context.isClassRegistered(testClass)) {
            return;
        }

        final Section section = Util.getAnnotationFromClass(testClass, Section.class);
        context.registerSection(section);
        context.registerClass(testClass);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        final Method testMethod = extensionContext.getRequiredTestMethod();
        final TckTest tckTest = Util.getAnnotationFromMethod(testMethod, TckTest.class);
        final TckTestResult testResult = TckTestResult.of(tckTest,
                extensionContext.getExecutionException().orElse(null));
        context.addTestResult(testResult);
    }
}
