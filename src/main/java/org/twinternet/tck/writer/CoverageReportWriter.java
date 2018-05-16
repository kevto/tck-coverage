package org.twinternet.tck.writer;

import org.twinternet.tck.CoverageReportProperties;
import org.twinternet.tck.TckTest;
import org.twinternet.tck.TckTestResult;

import java.util.List;

public interface CoverageReportWriter {

    void writeMetadata(CoverageReportProperties jsrProperties);

    void writeGlobalResults(final Double coveragePercentage, final List<String> failedSections);

    void writeSectionResults(final String id, final String description, final TckTestResult result);

}
