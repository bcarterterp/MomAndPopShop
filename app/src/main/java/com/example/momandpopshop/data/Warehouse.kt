package com.example.momandpopshop.data

interface Warehouse {

    fun getRestock(stockQuantity: Int): Map<Item, Int>

    fun orderNewProduct(item: Item, stockQuantity: Int): Pair<Item, Int>

}

open class WarehouseImpl : Warehouse {

    override fun getRestock(stockQuantity: Int): Map<Item, Int> {
        Thread.sleep(5000)
        return Item.singleList.map { it to stockQuantity }.toMap()
    }

    override fun orderNewProduct(item: Item, stockQuantity: Int): Pair<Item, Int> {
        Thread.sleep(5000)
        return item to stockQuantity
    }

}