package com.example.momandpopshop.data

class Shopper(initialCash: Int = 100) {

    var items: Map<Item, Int> = emptyMap()
        private set
    var cash: Int = initialCash

    fun buyItem(item: Item) {
        val itemCount = items[item] ?: 1
        items = items.plus(item to itemCount)
        cash -= item.cost
    }
}