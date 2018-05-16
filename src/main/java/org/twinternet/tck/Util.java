package org.twinternet.tck;

import org.twinternet.tck.writer.OutputStreamWriter;
import org.twinternet.tck.writer.CoverageReportWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class Util {

    private Util() {
        // Empty.
    }

    static CoverageReportWriter getDefaultCoverageReportWriter() {
        return OutputStreamWriter.of(System.out);
    }

    public static File getFileFromResources(final String filename) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
        if (Objects.isNull(url)) {
            return null;
        }

        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            System.err.println("URISyntaxException occurred while trying to get a file from resources: " + e.toString());
            file = null;
        }

        if (Objects.nonNull(file) && file.exists()) {
            return file;
        }
        return null;
    }

    static <T extends Annotation> T getAnnotationFromClass(final Class<?> testCls, final Class<T> annotationCls) {
        final Optional<T> section = findAnnotation(testCls, annotationCls);
        return section.orElse(null);
    }

    static <T extends Annotation> T getAnnotationFromMethod(final Method method, final Class<T> annotationCls) {
        final Optional<T> tckTest = findAnnotation(method, annotationCls);
        return tckTest.orElse(null);
    }
}
