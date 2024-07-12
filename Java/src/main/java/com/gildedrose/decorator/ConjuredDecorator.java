package com.gildedrose.decorator;


import com.gildedrose.core.AdjustableItem;
import com.gildedrose.core.DecoratedAdjustableItem;

public class ConjuredDecorator extends DecoratedAdjustableItem {
    public ConjuredDecorator(AdjustableItem item) {
        super(item);
    }

    @Override
    public void adjustQuality() {
        // adjust twice as per spec
        super.adjustQuality();
        super.adjustQuality();
    }
}
