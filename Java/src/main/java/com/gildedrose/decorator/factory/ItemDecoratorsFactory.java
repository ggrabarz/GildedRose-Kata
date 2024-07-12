package com.gildedrose.decorator.factory;

import com.gildedrose.Item;
import com.gildedrose.core.AdjustableItem;
import com.gildedrose.core.BaseAdjustableItem;

public class ItemDecoratorsFactory {
    public static AdjustableItem decorate(Item item) {
        Decoration composed = Decorators.getComposedDecorators(item);
        return composed.apply(new BaseAdjustableItem(item));
    }

    public static void decorateAndUpdate(Item item) {
        decorate(item).adjust();
    }
}


