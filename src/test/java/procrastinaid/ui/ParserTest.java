package procrastinaid.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import procrastinaid.exception.ProcrastinAidException;

public class ParserTest {
    @Test
    public void testParseInputBySpace() {
        Parser parser = new Parser();
        parser.setCurrentInput("todo");

        Throwable exception = assertThrows(ProcrastinAidException.class, () -> {
            parser.parseInputBySpace();
        });
    }
}
