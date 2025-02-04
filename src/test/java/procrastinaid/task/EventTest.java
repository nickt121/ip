package procrastinaid.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import procrastinaid.exception.ProcrastinAidException;

public class EventTest {
    @Test
    public void testDateFormat(){
        Throwable exception = assertThrows(ProcrastinAidException.class, () -> {
            Event event = new Event("test", false, "2021/09/01 12:00", "2021-09-01 12:00");
        });
        assertEquals("Invalid date format. Please use the format yyyy-MM-dd HH:mm", exception.getMessage());
    }
}
