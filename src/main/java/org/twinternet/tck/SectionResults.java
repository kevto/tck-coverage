package org.twinternet.tck;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kevin Berendsen
 */
public final class SectionResults {
    
    private final Section section;
    
    private final List<TckTestResult> results;
    
    private SectionResults(final Section section, final List<TckTestResult> results) {
        this.section = section;
        this.results = results;
    }
    
    static SectionResults of(final Section section, final List<TckTestResult> results) {
        Objects.requireNonNull(section);
        Objects.requireNonNull(results);
        if (results.isEmpty()) {
            throw new IllegalArgumentException("results must contain at least one test result");
        }
        return new SectionResults(section, Collections.unmodifiableList(results));
    }
    
    public Section getSection() {
        return section;
    }
    
    public List<TckTestResult> getResults() {
        return results;
    }

}
