package com.gildedrose.core;

import com.gildedrose.Item;

public interface AdjustableItem {

    Item unwrap();

    void adjustQuality();

    void adjustSellIn();

    default void adjust() {
        adjustSellIn();
        adjustQuality();
    };
}
