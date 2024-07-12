package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

class GildedRoseTest {

    @DisplayName("Gilded Rose Inn inventory should end up with following quality after time passes by:")
    @MethodSource({"test_gilded_rose_items_quality_change_in_time", "test_conjured_items"})
    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    void test_gilded_rose_items_quality_change_in_time(ItemTestProps props) {
        // given
        GildedRose app = new GildedRose(new Item[]{
            new Item(props.itemName(), props.startingSellIn(), props.startingQuality())
        });

        // when
        for (int i = 0; i < props.daysPassed(); i++) {
            app.updateQuality();
        }

        // then
        Item item = app.items[0];
        assertAll(
            assertEquals(props.itemName(), item.name, "name"),
            assertEquals(props.expectedSellIn(), item.sellIn, "sell in days"),
            assertEquals(props.expectedQuality(), item.quality, "quality")
        );

    }

    private static Executable assertEquals(Object expected, Object actual, String subject) {
        return () -> Assertions.assertEquals(expected, actual, "Expected %s to be <%s> but was <%s>".formatted(subject, expected, actual));
    }

    /**
     * Taken from {@link TexttestFixture} output for 15 days in format (itemName, startingSellIn, startingQuality, daysPassed, expectedSellIn, expectedQuality).
     * This is a "reference snapshot" of how the Gilded Rose Inn legacy code deployed on production functioned before refactoring.
     */
    private static List<ItemTestProps> test_gilded_rose_items_quality_change_in_time() {
        return List.of(
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 0, 10, 20),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 1, 9, 19),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 2, 8, 18),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 3, 7, 17),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 4, 6, 16),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 5, 5, 15),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 6, 4, 14),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 7, 3, 13),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 8, 2, 12),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 9, 1, 11),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 10, 0, 10),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 11, -1, 8),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 12, -2, 6),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 13, -3, 4),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 14, -4, 2),
            new ItemTestProps("+5 Dexterity Vest", 10, 20, 15, -5, 0),
            new ItemTestProps("Aged Brie", 5, 0, 0, 5, 0),
            new ItemTestProps("Aged Brie", 5, 0, 1, 4, 1),
            new ItemTestProps("Aged Brie", 5, 0, 2, 3, 2),
            new ItemTestProps("Aged Brie", 5, 0, 3, 2, 3),
            new ItemTestProps("Aged Brie", 5, 0, 4, 1, 4),
            new ItemTestProps("Aged Brie", 5, 0, 5, 0, 5),
            new ItemTestProps("Aged Brie", 5, 0, 6, -1, 7),
            new ItemTestProps("Aged Brie", 5, 0, 7, -2, 9),
            new ItemTestProps("Aged Brie", 5, 0, 8, -3, 11),
            new ItemTestProps("Aged Brie", 5, 0, 9, -4, 13),
            new ItemTestProps("Aged Brie", 5, 0, 10, -5, 15),
            new ItemTestProps("Aged Brie", 5, 0, 11, -6, 17),
            new ItemTestProps("Aged Brie", 5, 0, 12, -7, 19),
            new ItemTestProps("Aged Brie", 5, 0, 13, -8, 21),
            new ItemTestProps("Aged Brie", 5, 0, 14, -9, 23),
            new ItemTestProps("Aged Brie", 5, 0, 15, -10, 25),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 0, 5, 7),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 1, 4, 6),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 2, 3, 5),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 3, 2, 4),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 4, 1, 3),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 5, 0, 2),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 6, -1, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 7, -2, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 8, -3, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 9, -4, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 10, -5, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 11, -6, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 12, -7, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 13, -8, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 14, -9, 0),
            new ItemTestProps("Elixir of the Mongoose", 5, 7, 15, -10, 0),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 0, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 1, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 2, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 3, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 4, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 5, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 6, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 7, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 8, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 9, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 10, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 11, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 12, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 13, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 14, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", 0, 80, 15, 0, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 0, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 1, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 2, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 3, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 4, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 5, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 6, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 7, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 8, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 9, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 10, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 11, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 12, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 13, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 14, -1, 80),
            new ItemTestProps("Sulfuras, Hand of Ragnaros", -1, 80, 15, -1, 80),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 0, 15, 20),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 1, 14, 21),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 2, 13, 22),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 3, 12, 23),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 4, 11, 24),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 5, 10, 25),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 6, 9, 27),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 7, 8, 29),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 8, 7, 31),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 9, 6, 33),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 10, 5, 35),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 11, 4, 38),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 12, 3, 41),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 13, 2, 44),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 14, 1, 47),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 15, 20, 15, 0, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 0, 10, 49),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 1, 9, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 2, 8, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 3, 7, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 4, 6, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 5, 5, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 6, 4, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 7, 3, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 8, 2, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 9, 1, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 10, 0, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 11, -1, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 12, -2, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 13, -3, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 14, -4, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 10, 49, 15, -5, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 0, 5, 49),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 1, 4, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 2, 3, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 3, 2, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 4, 1, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 5, 0, 50),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 6, -1, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 7, -2, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 8, -3, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 9, -4, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 10, -5, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 11, -6, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 12, -7, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 13, -8, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 14, -9, 0),
            new ItemTestProps("Backstage passes to a TAFKAL80ETC concert", 5, 49, 15, -10, 0)
        );
    }

    /**
     * This is a new dataset created for the Conjured items' requirement - these should *degrade* in quality twice as fast.
     * There is an implied assumption, that Conjured items are always regular items, regardless of their original nature.
     * The assumption stems from the fact, that {@link GildedRose} compares items by name using strict equality, so a
     * "Conjured Sulfuras, Hand of Ragnaros" will never equal "Sulfuras, Hand of Ragnaros", thus it cannot be treated as Legendary.
     * Similarly, a Conjured Aged Brie will never increase in value over time, etc.
     */
    private static List<ItemTestProps> test_conjured_items() {
        return List.of(
            // Mana cake is a totally new item - should be treated as a default regular item and degrade twice as fast.
            new ItemTestProps("Conjured Mana Cake", 5, 20, 0, 5, 20),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 1, 4, 18),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 2, 3, 16),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 3, 2, 14),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 4, 1, 12),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 5, 0, 10),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 6, -1, 6),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 7, -2, 2),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 8, -3, 0),
            new ItemTestProps("Conjured Mana Cake", 5, 20, 9, -4, 0),

            // Conjured known items should behave correctly too. Following should degrade twice as fast compared to non-conjured counterparts.
            new ItemTestProps("Conjured +5 Dexterity Vest", 10, 20, 0, 10, 20),
            new ItemTestProps("Conjured +5 Dexterity Vest", 10, 20, 5, 5, 10),
            new ItemTestProps("Conjured +5 Dexterity Vest", 10, 20, 10, 0, 0),
            new ItemTestProps("Conjured +5 Dexterity Vest", 10, 20, 15, -5, 0),
            new ItemTestProps("Conjured Elixir of the Mongoose", 5, 7, 0, 5, 7),
            new ItemTestProps("Conjured Elixir of the Mongoose", 5, 7, 3, 2, 1),
            new ItemTestProps("Conjured Elixir of the Mongoose", 5, 7, 4, 1, 0),
            new ItemTestProps("Conjured Elixir of the Mongoose", 5, 7, 15, -10, 0),

            // These should be now treated as regular conjured items and degrade instead of holding or increasing in quality over time.
            new ItemTestProps("Conjured Aged Brie", 5, 5, 1, 4, 3),
            new ItemTestProps("Conjured Aged Brie", 5, 0, 15, -10, 0),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20, 0, 15, 20),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20, 8, 7, 4),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20, 12, 3, 0),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20, 15, 0, 0),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 10, 49, 10, 0, 29),
            new ItemTestProps("Conjured Backstage passes to a TAFKAL80ETC concert", 10, 49, 11, -1, 25),

            // Sulfuras is special, as it starts with quality of 80 and does not conform to the "regular" limit of 50 max quality,
            // The Specification of "The Quality of an item is never more than 50" and "The Quality of an item is never negative" is wrong and not how current
            // GildedRose legacy "production" app works at all.
            //
            // Currently, an item with quality ge 50 will never increase in quality and an item with quality le 0 will never decrease,
            // BUT if they start with quality over 50 or negative, they are not capped and behave according to their type
            // (so an Aged Brie with negative quality can increase up to 50, a +5 Dexterity Vest with quality of 150 will decrease to 0, etc.)
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", 5, 80, 0, 5, 80),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", 5, 80, 5, 0, 70),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", 5, 80, 10, -5, 50),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", -1, 80, 0, -1, 80),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", -1, 80, 5, -6, 60),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", -1, 80, 10, -11, 40),
            new ItemTestProps("Conjured Sulfuras, Hand of Ragnaros", -1, 80, 24, -25, 0)
        );
    }
}

