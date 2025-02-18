package procrastinaid.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void testSetStatus() {
        ToDo todo = new ToDo("test", false, "");
        todo.setStatus(true);
        assertEquals(1, todo.getStatusInt());
    }

}
