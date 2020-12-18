package eu.balev.sonarcloud;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorImplTest {

  private static CalculatorIfc toTest;

  @BeforeAll
  public static void setUp() {
    toTest = new CalculatorImpl();
  }

  @ParameterizedTest
  @CsvSource({"1,2,3", "11,13,24", "0,0,0"})
  void testSum(int a, int b, int expected) {
    int actual = toTest.sum(a, b);
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("Should sort the array correctly")
  @MethodSource("arrayProvider")
  void testBubbleSort(int unsorted[], int expected[]) {
    toTest.bubbleSort(unsorted);
    Assertions.assertArrayEquals(unsorted, expected);
  }

  private static Stream<Arguments> arrayProvider() {
    return Stream.of(
        Arguments.of(new int[]{5, 3, 8}, new int[]{3, 5, 8}),
        Arguments.of(new int[]{15, 3, 8, 1}, new int[]{1, 3, 8, 15}),
        Arguments.of(new int[]{}, new int[]{}),
        Arguments.of(new int[]{1, 3, 1, 1}, new int[]{1, 1, 1, 3})
    );
  }

}
