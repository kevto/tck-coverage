package org.twinternet.tck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Section(id = @SectionId("1"), description = "Complete test suite.")
public class TestSuite {

    @TckTest(section = @SectionId("1"), id = "1.0.0", description = "Test method one")
    public void methodOne() {
        assertEquals("1", "1");
    }

    @TckTest(section = @SectionId("1"), id = "1.0.1", description = "Test method two")
    public void methodTwo() {
        assertTrue(true);
    }

    @TckTest(section = @SectionId("1"), id = "1.0.2", description = "Test method three")
    public void methodThree() {
        assertTrue(false);
    }

    @TckTest(section = @SectionId("1"), id = "1.0.3", description = "Test method four")
    public void methodFour() {
        throw new RuntimeException("TEST");
    }

}
