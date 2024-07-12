package com.gildedrose.decorator;

class AdjustBounded {
    // Constants for value bounds, should be injected but the spec does not require it
    public static final int UPPER_BOUND = 50;
    public static final int LOWER_BOUND = 0;

    /**
     * @return The max possible adjustment within the defined bounds.
     */
    public static int adjustBounded(Integer currentValue, Integer adjustment) {
        return switch (adjustment) {
            case Integer n when n > 0 -> Math.min(adjustment, UPPER_BOUND - currentValue);
            case Integer n when n < 0 -> Math.max(adjustment, LOWER_BOUND - currentValue);
            default -> 0;
        };
    }
}
