package com.gildedrose.decorator.factory;

import com.gildedrose.Item;

import static com.gildedrose.core.Inventory.Common.AGED_BRIE;
import static com.gildedrose.core.Inventory.Common.TAFKAL80ETC;
import static com.gildedrose.core.Inventory.Legendary.SULFURAS;

class ItemTypeResolver {
    private static final String CONJURED_PREFIX = "CONJURED ";

    public static ItemType resolve(Item item) {
        return switch (item.name) {
            case SULFURAS -> ItemType.LEGENDARY;
            case AGED_BRIE -> ItemType.CHEESE;
            case TAFKAL80ETC -> ItemType.TICKET;
            case String s when isConjured(s) -> ItemType.CONJURED;
            default -> ItemType.COMMON;
        };
    }

    private static boolean isConjured(String itemName) {
        return itemName.toUpperCase().startsWith(CONJURED_PREFIX);
    }

    enum ItemType {
        CHEESE,
        COMMON,
        CONJURED,
        LEGENDARY,
        TICKET,
    }
}


