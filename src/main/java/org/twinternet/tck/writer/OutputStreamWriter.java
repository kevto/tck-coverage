package org.twinternet.tck.writer;

import org.twinternet.tck.CoverageReportProperties;
import org.twinternet.tck.TckTestResult;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.twinternet.tck.Section;
import org.twinternet.tck.SectionResults;

public class OutputStreamWriter implements CoverageReportWriter {
    private static final String NL = System.getProperty("line.separator");
    private static final String DIVIDER = "####################################################################################" + NL;
    private static final String WS_4 = "    ";
    private final OutputStream outputStream;

    protected OutputStreamWriter(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public static OutputStreamWriter of(final OutputStream outputStream) {
        Objects.requireNonNull(outputStream);
        return new OutputStreamWriter(outputStream);
    }

    private void writeString(final String text) {
        try {
            outputStream.write(text.getBytes(StandardCharsets.US_ASCII));
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Couldn't write text to output stream: " + e.getMessage());
        }
    }

    @Override
    public void writeMetadata(CoverageReportProperties jsrProperties) {
        final StringBuilder metaHeader = new StringBuilder().append(NL)
                .append("   oooo   .oooooo.   ooooooooo.        ooooooooooooo   .oooooo.   oooo    oooo ").append(NL)
                .append("   `888  d8P'  `Y8b  `888   `Y88.      8'   888   `8  d8P'  `Y8b  `888   .8P'  ").append(NL)
                .append("    888 888           888   .d88'           888      888           888  d8'    ").append(NL)
                .append("    888 888           888ooo88P'            888      888           88888[      ").append(NL)
                .append("    888 888           888                   888      888           888`88b.    ").append(NL)
                .append("    888 `88b    ooo   888                   888      `88b    ooo   888  `88b.  ").append(NL)
                .append(".o. 88P  `Y8bood8P'  o888o                 o888o      `Y8bood8P'  o888o  o888o ").append(NL)
                .append("`Y888P ").append(NL)
                .append(NL).append(DIVIDER).append(NL)
                .append("JSR:\t\t\t").append(jsrProperties.getJsrId()).append(NL)
                .append("Version:\t\t").append(jsrProperties.getJsrVersion()).append(NL)
                .append("Description:\t\t").append(jsrProperties.getJsrDescription()).append(NL)
                .append("URL:\t\t\t").append(jsrProperties.getJsrUrl()).append(NL)
                .append("License:\t\t").append(jsrProperties.getJsrLicenseType()).append(NL)
                .append("License URL:\t\t").append(jsrProperties.getJsrLicenseUrl()).append(NL)
                .append("Copyright:\t\t").append(jsrProperties.getJsrCopyright()).append(NL)
                .append(NL).append(DIVIDER).append(NL);
        writeString(metaHeader.toString());
    }

    @Override
    public void writeGlobalResults(Float coveragePercentage, List<TckTestResult> failedTests) {
        final StringBuilder globalResults =
                new StringBuilder("Coverage Rate:\t\t" + coveragePercentage + "%" + NL);
        if (!failedTests.isEmpty()) {
            globalResults.append("Failed Tests:\t\t").append(failedTests.size()).append(NL).append(NL);

            for (TckTestResult failedTest : failedTests) {
                globalResults.append("[ERROR]\t").append(failedTest.getId()).append(" ").append(failedTest.getDescription()).append(NL);
                globalResults.append(WS_4).append("Type: ").append(failedTest.getThrowableType()).append(NL);
                globalResults.append(WS_4).append("Cause: ");
                final Optional<Throwable> throwable = failedTest.getThrowable();
                if (throwable.isPresent()) {
                    globalResults.append(throwable.get().getMessage());
                } else {
                    globalResults.append("<throwable NOT found>");
                }
                globalResults.append(NL);
            }
        }
        globalResults.append(NL).append(DIVIDER).append(NL);
        writeString(globalResults.toString());
    }

    @Override
    public void writeSectionResults(final List<SectionResults> sectionResults) {
        for (SectionResults sr : sectionResults)
            writeSingleSectionResults(sr);
    }
    
    private void writeSingleSectionResults(final SectionResults sectionResults) {
        final String header = String.format("[%s] %s", 
                sectionResults.getSection().id().value(),
                sectionResults.getSection().description());
        writeResults(header, sectionResults.getResults());
    }

    @Override
    public void writeResults(String header, List<TckTestResult> results) {
        final StringBuilder sectionBuilder = new StringBuilder()
                .append(header).append(NL);
        for (TckTestResult r : results) {
            sectionBuilder.append(WS_4).append("[").append(r.getThrowableType()).append("] ")
                    .append(r.getId()).append(" ").append(r.getDescription())
                    .append(NL);
        }
        sectionBuilder.append(NL).append(DIVIDER).append(NL);
        writeString(sectionBuilder.toString());
    }
}
