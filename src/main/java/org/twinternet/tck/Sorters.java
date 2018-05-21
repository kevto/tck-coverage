package org.twinternet.tck;

import java.util.Comparator;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class Sorters {

    private Sorters() {
        // Prevent instantiation.
    }

    static final class TestResultSorter implements Comparator<TckTestResult> {

        private TestResultSorter() {}
        
        @Override
        public int compare(TckTestResult left, TckTestResult right) {
            try {
                final IdTokenizer tokenizerLeft = IdTokenizer.of(left.getId());
                final IdTokenizer tokenizerRight = IdTokenizer.of(right.getId());
                return tokenizerLeft.compareTo(tokenizerRight);
            } catch (IllegalArgumentException e) {
                return 0;
            }
        }
    
    }

    static int sortTestResults(TckTestResult left, TckTestResult right) {
        return new TestResultSorter().compare(left, right);
    }
    
    static final class SectionSorter implements Comparator<Section> {
        
        private SectionSorter() {}
        
        @Override
        public int compare(Section left, Section right) {
            try {
                final IdTokenizer tokenizerLeft = IdTokenizer.of(left.id().value());
                final IdTokenizer tokenizerRight = IdTokenizer.of(right.id().value());
                return tokenizerLeft.compareTo(tokenizerRight);
            } catch (IllegalArgumentException e) {
                return 0;
            }
        }
        
    }
    
    static int sortSections(Section left, Section right) {
        return new SectionSorter().compare(left, right);
    }
}
