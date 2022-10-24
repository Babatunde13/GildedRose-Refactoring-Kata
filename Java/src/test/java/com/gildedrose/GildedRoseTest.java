package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void sulfurasItem() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();
        Item sulfuras = app.getItems()[0];

        assertEquals("Sulfuras, Hand of Ragnaros", sulfuras.name);
        assertEquals(80, sulfuras.quality);
        assertEquals(0, sulfuras.sellIn);
    }

    @Test
    void agedBrieItem() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 3, 49),
            new Item("Aged Brie", 2, 0)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        Item agedBrie1 = app.getItems()[0];
        assertEquals("Aged Brie", agedBrie1.name);
        assertEquals(0, agedBrie1.sellIn);
        assertEquals(50, agedBrie1.quality);

        Item agedBrie2 = app.getItems()[1];
        assertEquals("Aged Brie", agedBrie2.name);
        assertEquals(-1, agedBrie2.sellIn);
        assertEquals(3, agedBrie2.quality);
    }

    @Test
    void conjuredItem() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 1, 11),
            new Item("Conjured Mana Cake", 1, 3)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        Item conjured1 = app.getItems()[0];
        assertEquals("Conjured Mana Cake", conjured1.name);
        assertEquals(-2, conjured1.sellIn);
        assertEquals(3, conjured1.quality);

        Item conjured2 = app.getItems()[1];
        assertEquals("Conjured Mana Cake", conjured2.name);
        assertEquals(-2, conjured2.sellIn);
        assertEquals(0, conjured2.quality);
    }

    @Test
    void defaultItem() {
        Item[] items = new Item[] {
            new Item("Random Item", 3, 10),
            new Item("Random Item 2", 1, 12),
            new Item("Random Item 3", 4, 2),
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        Item item1 = app.getItems()[0];
        Item item2 = app.getItems()[1];
        Item item3 = app.getItems()[2];

        assertEquals("Random Item", item1.name);
        assertEquals(0, item1.sellIn);
        assertEquals(7, item1.quality);

        assertEquals("Random Item 2", item2.name);
        assertEquals(-2, item2.sellIn);
        assertEquals(7, item1.quality);

        assertEquals("Random Item 3", item3.name);
        assertEquals(1, item3.sellIn);
        assertEquals(0, item3.quality);
    }
}
