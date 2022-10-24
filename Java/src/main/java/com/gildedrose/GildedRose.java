package com.gildedrose;

class GildedRose {
    private Item[] items;
    private final int MIN_QUALITY = 0;
    private final int MAX_QUALITY = 50;
    private final int SULFURAS_MAX_QUALITY = 80;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return this.items;
    }

    private void updateAgedBrieItemQuality(Item item) {
        if (item.quality < this.MAX_QUALITY) {
            if (item.sellIn >= 0) {
                item.quality = Math.min(item.quality + 1, this.MAX_QUALITY);
            } else {
                item.quality = Math.min(item.quality + 2, this.MAX_QUALITY);
            }
        }
    }

    private void updateBackStageTAFKAL80ETCItemQuality(Item item) {
        if (item.quality >= this.MAX_QUALITY) {
            return;
        }

        if (item.sellIn < 0) {
            item.quality = 0;
        } else if(item.sellIn < 6) {
            item.quality = Math.min(item.quality + 3, this.MAX_QUALITY);
        } else if (item.sellIn < 11) {
            item.quality = Math.min(item.quality + 2, this.MAX_QUALITY);
        } else {
            item.quality = Math.min(item.quality + 1, this.MAX_QUALITY);
        }
    }

    private void updateConjureItem(Item item) {
        if (item.quality > 0) {
            if (item.sellIn >= 0) {
                item.quality = Math.max(item.quality - 2, this.MIN_QUALITY);
            } else {
                item.quality = Math.max(item.quality - 4, this.MIN_QUALITY);
            }
        }
    }

    private void updateDefaultItemQuality(Item item) {
        if (item.sellIn >= 0) {
            item.quality = Math.max(item.quality - 1, this.MIN_QUALITY);
        } else {
            item.quality = Math.max(item.quality - 2, this.MIN_QUALITY);
        }
    }

    private void updateItemSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }
    }

    private boolean isItemQualityInRange(Item item) {
        return item.quality >= this.MIN_QUALITY && item.quality <= this.MAX_QUALITY;
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
            item.quality = this.SULFURAS_MAX_QUALITY;
            return;
        }

        if (this.isItemQualityInRange(item)) {
            if (item.name.equals("Aged Brie")) {
                this.updateAgedBrieItemQuality(item);
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                this.updateBackStageTAFKAL80ETCItemQuality(item);
            } else if (item.name.equals("Conjured Mana Cake")) {
                this.updateConjureItem(item);
            } else {
                this.updateDefaultItemQuality(item);
            }
        }
    }

    public void updateQuality() {
        for (Item item: items) {
            this.updateItemQuality(item);
            this.updateItemSellIn(item);
        }
    }
}
