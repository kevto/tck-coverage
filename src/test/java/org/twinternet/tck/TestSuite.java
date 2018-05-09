package org.twinternet.tck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Section(id = "1", description = "Complete test suite.")
public class TestSuite {

    @TckTest(section = "1", id = "1.0.0", description = "Test method one")
    public void methodOne() {
        assertEquals("1", "1");
    }

    @TckTest(section = "1", id = "1.0.1", description = "Test method two")
    public void methodTwo() {
        assertTrue(true);
    }

}
