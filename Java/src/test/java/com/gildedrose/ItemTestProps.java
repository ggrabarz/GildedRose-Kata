package com.gildedrose;

public record ItemTestProps(
    String itemName,
    int startingSellIn,
    int startingQuality,
    int daysPassed,
    int expectedSellIn,
    int expectedQuality
) {
    @Override
    public String toString() {
        return STR."\{itemName} of quality \{startingQuality} and \{startingSellIn} expiry days should end up with quality \{expectedQuality} and \{expectedSellIn} expiry days after \{daysPassed} days";
    }
}
