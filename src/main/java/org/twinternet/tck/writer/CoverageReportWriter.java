package org.twinternet.tck.writer;

import org.twinternet.tck.CoverageReportProperties;
import org.twinternet.tck.TckTestResult;
import java.util.List;
import org.twinternet.tck.SectionResults;

public interface CoverageReportWriter {

    void writeMetadata(CoverageReportProperties jsrProperties);

    void writeGlobalResults(final Float coveragePercentage, final List<TckTestResult> failedTests);

    void writeSectionResults(final List<SectionResults> sectionResults);
    
    void writeResults(final String header, final List<TckTestResult> results);

}
