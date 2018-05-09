package org.twinternet.tck.writer;

import org.twinternet.tck.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * TODO Use Apache Velocity
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class TemplateParserWriter implements CoverageReportWriter {

    private static final String DEFAULT_TEMPLATE = "coverage-report.html";

    private static final String DEFAULT_STYLESHEET = "coverage-report.css";

    private static final String DEFAULT_LOGO = "oracle.png";

    private final File outputFile;

    private final File templateFile;

    private final File stylesheetFile;

    private final File logoFile;

    private TemplateParserWriter(final File outputFile, final File templateFile, final File stylesheetFile, final File logoFile) {
        this.outputFile = outputFile;
        this.templateFile = templateFile;
        this.stylesheetFile = stylesheetFile;
        this.logoFile = logoFile;
    }

    public static TemplateParserWriter of(final File outputFile) throws FileNotFoundException {
        Objects.requireNonNull(outputFile);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        final File templateFile = Util.getFileFromResources(DEFAULT_TEMPLATE);
        if (Objects.isNull(templateFile)) {
            throw new FileNotFoundException("Couldn't find: " + DEFAULT_TEMPLATE);
        }

        final File stylesheetFile = Util.getFileFromResources(DEFAULT_STYLESHEET);
        if (Objects.isNull(stylesheetFile)) {
            throw new FileNotFoundException("Couldn't find: " + DEFAULT_STYLESHEET);
        }

        final File logoFile = Util.getFileFromResources(DEFAULT_LOGO);
        if (Objects.isNull(logoFile)) {
            throw new FileNotFoundException("Couldn't find: " + DEFAULT_LOGO);
        }

        return new TemplateParserWriter(outputFile, templateFile, stylesheetFile, logoFile);
    }



}
