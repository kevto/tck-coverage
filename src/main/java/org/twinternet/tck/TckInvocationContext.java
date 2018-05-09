package org.twinternet.tck;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TckInvocationContext implements TestTemplateInvocationContext {

    private final String id, description;

    private TckInvocationContext(final String id, final String description) {
        this.id = id;
        this.description = description;
    }

    static TckInvocationContext of(final String id, final String description) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(description);
        return new TckInvocationContext(id, description);
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return "TCK Test [" + id + ", \"" + description + "\"]";
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return Arrays.asList(new TckTestExecutionExtension());
    }
}
