package com.gildedrose.core;

import com.gildedrose.Item;
import com.gildedrose.core.AdjustableItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoratedAdjustableItem implements AdjustableItem {
    protected final AdjustableItem item;

    @Override
    public Item unwrap() {
        return item.unwrap();
    }

    @Override
    public void adjustQuality() {
        this.item.adjustQuality();
    }

    @Override
    public void adjustSellIn() {
        this.item.adjustSellIn();
    }
}
