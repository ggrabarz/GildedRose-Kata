package com.gildedrose;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[]{
//            new Item("+5 Dexterity Vest", 10, 20), //
//            new Item("Aged Brie", 5, 0), //
//            new Item("Elixir of the Mongoose", 5, 7), //
//            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
             new Item("Conjured Mana Cake", 3, 6)
        };

        int days = 15;
        for (Item item : items) {
            GildedRose app = new GildedRose(new Item[]{item});
            int startingQuality = item.quality;
            int startingSellIn = item.sellIn;
            for (int i = 0; i <= days; i++) {
                System.out.println(STR."new ItemTestProps(\"\{item.name}\", \{startingSellIn}, \{startingQuality}, \{i}, \{item.sellIn}, \{item.quality}),");
                app.updateQuality();
            }
        }

    }
}
