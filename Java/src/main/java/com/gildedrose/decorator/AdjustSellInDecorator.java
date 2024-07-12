package com.gildedrose.decorator;

import com.gildedrose.core.AdjustableItem;
import com.gildedrose.core.DecoratedAdjustableItem;

public class AdjustSellInDecorator extends DecoratedAdjustableItem {
    public AdjustSellInDecorator(AdjustableItem item) {
        super(item);
    }

    @Override
    public void adjustSellIn() {
        super.adjustSellIn();
        item.unwrap().sellIn -= 1;
    }
}
