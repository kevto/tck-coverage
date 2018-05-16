package org.twinternet.tck;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class IdTokenizer implements Comparable<IdTokenizer> {

    private final List<String> rawDelimited;

    private IdTokenizer(final List<String> rawDelimited) {
        this.rawDelimited = rawDelimited;
    }

    static IdTokenizer of(final String raw) {
        Objects.requireNonNull(raw);
        final List<String> rawDelimited = Arrays.asList(raw.trim().split("\\."));
        final Pattern pattern = Pattern.compile("[0-9]+");
        for (String partial : rawDelimited) {
            if (!pattern.matcher(partial).matches()) {
                throw new IllegalArgumentException("Non numeric character found in ID.");
            }
        }
        return new IdTokenizer(rawDelimited);
    }

    List<Integer> tokenize() {
        return rawDelimited.stream().map(Integer::parseInt)
                .collect(CollectorsExt.toLinkedList());
    }

    @Override
    public int compareTo(IdTokenizer o) {
        final List<Integer> tokenizedLeft = this.tokenize();
        final List<Integer> tokenizedRight = o.tokenize();
        for (int i = 0; i < tokenizedLeft.size(); i++) {
            if (tokenizedRight.size() < i+1) {
                return 1;
            }
            if (tokenizedLeft.get(i) > tokenizedRight.get(i)) {
                return 1;
            } else if(tokenizedLeft.get(i) < tokenizedRight.get(i)) {
                return -1;
            }
        }
        if (tokenizedRight.size() > tokenizedLeft.size()) {
            return -1;
        }
        return 0;
    }
}
