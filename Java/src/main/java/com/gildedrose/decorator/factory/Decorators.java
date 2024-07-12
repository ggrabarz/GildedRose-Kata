package com.gildedrose.decorator.factory;

import com.gildedrose.Item;
import com.gildedrose.decorator.AdjustQualityDecorator;
import com.gildedrose.decorator.AdjustSellInDecorator;
import com.gildedrose.decorator.ConjuredDecorator;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Decorators {
    public static Decoration getComposedDecorators(Item item) {
        return getApplicableDecorators(item).reduce(
            identity -> identity,
            (l, r) -> (it) -> r.apply(l.apply(it))
        );
    }

    private static Stream<Decoration> getApplicableDecorators(Item item) {
        return switch (ItemTypeResolver.resolve(item)) {
            case CHEESE -> brieDecorators();
            case COMMON -> commonDecorators();
            case CONJURED -> conjuredDecorators();
            case LEGENDARY -> legendaryDecorators();
            case TICKET -> ticketDecorators();
        };
    }

    private static Stream<Decoration> adjustQualityDecorators(int amount) {
        return Stream.of(
            item -> new AdjustQualityDecorator(item, always(), amount),
            item -> new AdjustQualityDecorator(item, sellInUnder(0), amount)
        );
    }

    private static Stream<Decoration> brieDecorators() {
        return merge(
            adjustSellInDecorator(),
            adjustQualityDecorators(1)
        );
    }

    private static Stream<Decoration> conjuredDecorators() {
        return merge(
            commonDecorators(),
            Stream.of(ConjuredDecorator::new)
        );
    }

    private static Stream<Decoration> commonDecorators() {
        return merge(
            adjustSellInDecorator(),
            adjustQualityDecorators(-1)
        );
    }

    private static Stream<Decoration> legendaryDecorators() {
        return Stream.of();
    }

    private static Stream<Decoration> adjustSellInDecorator() {
        return Stream.of(AdjustSellInDecorator::new);
    }

    private static Stream<Decoration> ticketDecorators() {
        return merge(
            adjustSellInDecorator(),
            Stream.of(
                item -> new AdjustQualityDecorator(item, always(), 1),
                item -> new AdjustQualityDecorator(item, sellInUnder(10), 1),
                item -> new AdjustQualityDecorator(item, sellInUnder(5), 1),
                item -> new AdjustQualityDecorator(item, sellInUnder(0), -50)
            ));
    }

    private static Predicate<Item> sellInUnder(int under) {
        return item -> item.sellIn < under;
    }

    private static Predicate<Item> always() {
        return _ -> true;
    }

    @SafeVarargs
    private static <T> Stream<T> merge(Stream<T>... streams) {
        return Stream.of(streams).reduce(Stream.of(), Stream::concat);
    }
}
