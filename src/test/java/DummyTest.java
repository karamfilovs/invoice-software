import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DummyTest {

    @Test
    @DisplayName("Can sum positive numbers")
    public void canSumNumbers(){
        Assertions.assertEquals(5, 2+3, "The numbers are not equal");
        Assertions.assertEquals("test", "test");
    }
}
