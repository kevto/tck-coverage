package org.twinternet.tck.writer;

import org.twinternet.tck.CoverageReportProperties;
import org.twinternet.tck.TckTestResult;

import java.util.List;

public final class ConsoleWriter implements CoverageReportWriter {

    private ConsoleWriter() {
        // Empty.
    }

    public static ConsoleWriter of() {
        return new ConsoleWriter();
    }

    @Override
    public void writeMetadata(CoverageReportProperties jsrProperties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeGlobalResults(Double coveragePercentage, List<String> failedSections) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSectionResults(String id, String description, TckTestResult result) {
        throw new UnsupportedOperationException();
    }
}
