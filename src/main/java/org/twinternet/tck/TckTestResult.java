package org.twinternet.tck;

import org.opentest4j.AssertionFailedError;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class TckTestResult {

    private final TckTest tckTest;

    private final Throwable throwable;

    private final ThrowableType throwableType;

    private TckTestResult(final TckTest tckTest, final Throwable throwable, final ThrowableType throwableType) {
        this.tckTest = tckTest;
        this.throwable = throwable;
        this.throwableType = throwableType;
    }

    static TckTestResult of(final TckTest tckTest, final Throwable throwable) {
        Objects.requireNonNull(tckTest);
        final ThrowableType throwableType;
        if (Objects.isNull(throwable)) {
            throwableType = ThrowableType.SUCCESS;
        } else if (AssertionFailedError.class.isInstance(throwable)) {
            throwableType = ThrowableType.FAILED_WITH_ASSERTION_ERROR;
        } else {
            throwableType = ThrowableType.FAILED_WITH_EXCEPTION;
        }
        return new TckTestResult(tckTest, throwable, throwableType);
    }

    String getId() {
        return tckTest.id();
    }

    String getSectionId() {
        return tckTest.section().value();
    }

    String getDescription() {
        return tckTest.description();
    }

    ThrowableType getThrowableType() {
        return throwableType;
    }

    Optional<Throwable> getThrowable() {
        return Optional.ofNullable(throwable);
    }

    enum ThrowableType {
        /**
         * If neither an exception or assertion error occured.
         */
        SUCCESS,
        /**
         * When an exception occurred that wasn't most likely expected.
         */
        FAILED_WITH_EXCEPTION,
        /**
         * An assertion error/failure.
         */
        FAILED_WITH_ASSERTION_ERROR;
    }
}
