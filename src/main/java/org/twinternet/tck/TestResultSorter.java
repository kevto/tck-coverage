package org.twinternet.tck;

import java.util.Comparator;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class TestResultSorter implements Comparator<TckTestResult> {

    private static TestResultSorter COMPARATOR = new TestResultSorter();


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

    static int compareResults(TckTestResult left, TckTestResult right) {
        return COMPARATOR.compare(left, right);
    }
}
