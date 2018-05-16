package org.twinternet.tck.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class DefaultFileWriter extends OutputStreamWriter {

    private DefaultFileWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public static DefaultFileWriter of(final File file) throws FileNotFoundException {
        Objects.requireNonNull(file);
        return new DefaultFileWriter(new FileOutputStream(file));
    }
}
