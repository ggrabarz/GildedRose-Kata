package com.gildedrose;

import com.gildedrose.decorator.factory.ItemDecoratorsFactory;

import java.util.stream.Stream;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Stream.of(items).forEach(ItemDecoratorsFactory::decorateAndUpdate);
    }
}
