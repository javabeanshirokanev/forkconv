import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.mocks.InnerTool;
import org.test.mocks.Tool;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExampleTest {
    @Mock
    private InnerTool innerTool;
    @InjectMocks
    private Tool tool;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this); // инициализация моков
    }

    @Test
    void mockTest() {
        when(innerTool.getName()).thenReturn("TEST");
        String name = tool.getUser();
        assertEquals(name, "Name is TEST");
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 5, 15",
            "7, 8, 15"
    })
    void sum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    static Stream<Arguments> prov() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(10, 20, 30),
                Arguments.of(5, 7, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("prov")
    void providerSum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/file.csv", numLinesToSkip = 0)
    void csvSum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    @Test
    void test() {
        Assertions.assertTrue(true);
    }

    @TestFactory
    Stream<DynamicTest> massiveTest() {
        return Stream.of(
                DynamicTest.dynamicTest("1 = 1", () -> assertEquals(1,1)),
                DynamicTest.dynamicTest("1 <> 2", () -> Assertions.assertNotEquals(1,2))
        );
    }

    @RepeatedTest(5)
    void testRandomBehaviour() {
        Random rnd = new Random();
        int val = rnd.nextInt(10);
        int res = val * 2;
        assertEquals(res, val * 2);
    }
}
