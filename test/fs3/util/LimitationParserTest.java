package fs3.util;

import fs3.enums.Limitation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitationParserTest {
    @Test
    void testParse() {
        assertEquals(LimitationLevelParser.parseInt(0), Limitation.NO_LIMITATION);
    }
}