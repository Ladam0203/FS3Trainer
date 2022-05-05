package fs3.util;

import fs3.enums.LimitationLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitationLevelParserTest {
    @Test
    void testParse() {
        assertEquals(LimitationLevelParser.parseInt(0), LimitationLevel.NO_LIMITATION);
    }
}