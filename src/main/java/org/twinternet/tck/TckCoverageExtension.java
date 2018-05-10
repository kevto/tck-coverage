package org.twinternet.tck;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.stream.Stream;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

public class TckCoverageExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        return findAnnotation(extensionContext.getRequiredTestMethod(), TckTest.class).isPresent()
                && findAnnotation(extensionContext.getRequiredTestClass(), Section.class).isPresent();
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        final TckTest annotation = findAnnotation(extensionContext.getRequiredTestMethod(), TckTest.class)
                .orElseThrow(() -> new AssertionError("Could not find annotation: " + TckTest.class.getName()));
        return Stream.of(extensionContext)
                .map(context -> TckInvocationContext.of(annotation.id(), annotation.description()));
    }
}
