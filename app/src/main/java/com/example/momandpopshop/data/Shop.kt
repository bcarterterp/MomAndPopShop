package com.example.momandpopshop.data

class Shop {

    var storeItems: Map<Item, Int> = emptyMap()
        private set

    fun stockItems(numOfEachItem: Int) {
        storeItems = Item.singleList.map { it to numOfEachItem }.toMap()
    }

    fun buyItem(item: Item, shopper: Shopper): Boolean {
        val itemCount = storeItems[item] ?: 0
        if (itemCount > 0 && shopper.cash >= item.cost) {
            storeItems = storeItems.plus(item to itemCount - 1)
            shopper.buyItem(item)
            return true
        }
        return false
    }
}

