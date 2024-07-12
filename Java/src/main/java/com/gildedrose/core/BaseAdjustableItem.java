package com.gildedrose.core;

import com.gildedrose.Item;

public record BaseAdjustableItem(Item item) implements AdjustableItem {
    @Override
    public Item unwrap() {
        return item;
    }

    @Override
    public void adjustQuality() {
        // no operation by default - also Legendary items' behaviour
    }

    @Override
    public void adjustSellIn() {
        // no operation by default - also Legendary items' behaviour
    }
}
