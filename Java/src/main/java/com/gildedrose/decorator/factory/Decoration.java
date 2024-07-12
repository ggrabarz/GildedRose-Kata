package com.gildedrose.decorator.factory;

import com.gildedrose.core.AdjustableItem;

import java.util.function.Function;

/**
 * A function that allows to decorate an {@link AdjustableItem} and then returns the top-most Decorator wrapper reference.
 */
public interface Decoration extends Function<AdjustableItem, AdjustableItem> {}
