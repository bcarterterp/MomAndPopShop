package com.example.momandpopshop.data

class Shop(private val warehouse: Warehouse) {

    var storeItems: Map<Item, Int> = emptyMap()
        private set

    fun stockItems(numOfEachItem: Int) {
        storeItems = warehouse.getRestock(numOfEachItem)
    }

    fun stockNewItem(item: Item, stockCount: Int) {
        storeItems = storeItems.plus(warehouse.orderNewProduct(item, stockCount))
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

