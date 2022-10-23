package com.gildedrose;

class GildedRose {
    private Item[] items;
    private final int MIN_QUALITY = 0;
    private final int MAX_QUALITY = 50;
    private final int SULFURAS_MAX_QUALITY = 80;

    public GildedRose(Item[] items) {
        for (Item item: items) {
            this.limitItemQuality(item); // limit item quality to max or min if out of range
        }
        this.items = items;
    }

    private void limitItemQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")){
            item.quality = SULFURAS_MAX_QUALITY;
        } else if (item.quality > this.MAX_QUALITY) {
            item.quality = this.MAX_QUALITY;
        } else if (item.quality < this.MIN_QUALITY) {
            item.quality = this.MIN_QUALITY;
        }
    }

    public Item[] getItems() {
        return this.items;
    }

    private void updateAgedBrieItemQuality(Item item) {
        if (item.quality < this.MAX_QUALITY) {
            System.out.println(item.quality);
            if (item.sellIn >= 0) {
                item.quality += 1;
            } else {
                item.quality += 2;
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
            item.quality += 3;
        } else if (item.sellIn < 11) {
            item.quality += 2;
        } else {
            item.quality++;
        }
    }

    private void updateConjureItem(Item item) {
        if (item.quality > 0) {
            if (item.sellIn >= 0) {
                item.quality -= 2;
            } else {
                item.quality -= 4;
            }
        }
    }

    private void updateDefaultItemQuality(Item item) {
        if (item.sellIn >= 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }
    }

    private void updateItemSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
            return;
        }

        if (item.quality > this.MIN_QUALITY && item.quality < this.MAX_QUALITY) {
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
