package org.twinternet.tck.writer;

public final class ConsoleWriter implements CoverageReportWriter {

    private ConsoleWriter() {
        // Empty.
    }

    public static ConsoleWriter of() {
        return new ConsoleWriter();
    }
}
