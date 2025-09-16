import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExampleTest {
    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 5, 15",
            "7, 8, 15"
    })
    //@MethodSource("stringProvider")
    public void sum(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }
}
