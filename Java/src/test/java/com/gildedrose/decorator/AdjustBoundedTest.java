package com.gildedrose.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.gildedrose.decorator.AdjustBounded.LOWER_BOUND;
import static com.gildedrose.decorator.AdjustBounded.UPPER_BOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AdjustBoundedTest {
    private static final int OVERFLOW = 10;
    private static final int RANGE_BOTTOM = LOWER_BOUND - OVERFLOW;
    private static final int RANGE_TOP = UPPER_BOUND + OVERFLOW;
    private static final int ADJUST_BY = 3;

    @DisplayName("Test bounded value adjustment in four different combinations")
    @MethodSource({
        "test_increasing_with_positive_adjustment",
        "test_increasing_with_negative_adjustment",
        "test_decreasing_with_positive_adjustment",
        "test_decreasing_with_negative_adjustment",
    })
    @ParameterizedTest
    void test_bounded_adjustments(int currentValue, int requestedAdjustment, int expectedAdjustment) {
        int actualAdjustment = AdjustBounded.adjustBounded(currentValue, requestedAdjustment);
        assertEquals(expectedAdjustment, actualAdjustment);
    }

    public static Stream<Arguments> test_increasing_with_positive_adjustment() {
        return rangeIncreasing().mapToObj(i -> arguments(i, ADJUST_BY, Math.min(ADJUST_BY, UPPER_BOUND - i)));
    }

    public static Stream<Arguments> test_increasing_with_negative_adjustment() {
        return rangeIncreasing().mapToObj(i -> arguments(i, -ADJUST_BY, Math.max(-ADJUST_BY, LOWER_BOUND - i)));
    }

    public static Stream<Arguments> test_decreasing_with_positive_adjustment() {
        return rangeDecreasing().mapToObj(i -> arguments(i, ADJUST_BY, Math.min(ADJUST_BY, UPPER_BOUND - i)));
    }

    public static Stream<Arguments> test_decreasing_with_negative_adjustment() {
        return rangeDecreasing().mapToObj(i -> arguments(i, -ADJUST_BY, Math.max(-ADJUST_BY, LOWER_BOUND - i)));
    }

    private static IntStream rangeIncreasing() {
        return IntStream.range(RANGE_BOTTOM, RANGE_TOP);
    }

    private static IntStream rangeDecreasing() {
        return rangeIncreasing().map(i -> -i);
    }
}
