package com.gildedrose.decorator;


import com.gildedrose.Item;
import com.gildedrose.core.AdjustableItem;
import com.gildedrose.core.DecoratedAdjustableItem;

import java.util.function.Predicate;

public class AdjustQualityDecorator extends DecoratedAdjustableItem {
    private final int amount;
    private final Predicate<Item> condition;

    public AdjustQualityDecorator(AdjustableItem item, Predicate<Item> condition, int amount) {
        super(item);
        this.condition = condition;
        this.amount = amount;
    }

    @Override
    public void adjustQuality() {
        super.adjustQuality();
        Item item = this.item.unwrap();
        if (condition.test(item)) {
            item.quality += AdjustBounded.adjustBounded(item.quality, amount);
        }
    }
}
