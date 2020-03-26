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
        }
        return true
    }
}

sealed class Item(val name: String, val cost: Int, val category: Category) {

    companion object {
        val singleList = listOf(
            Eggs, Bread,
            DogTreat, DogCollar,
            Marbles, Cards
        )
    }

    enum class Category {
        FOOD,
        PET_SUPPLIES,
        TOYS
    }


    object Eggs : Item("Granny Smith Apple", 1, Category.FOOD)
    object Bread : Item("Potato Bread", 2, Category.FOOD)
    object DogTreat : Item("Puppy Snax", 1, Category.PET_SUPPLIES)
    object DogCollar : Item("Dog Collar", 2, Category.PET_SUPPLIES)
    object Marbles : Item("Marble Set", 1, Category.TOYS)
    object Cards : Item("Deck of Cards", 2, Category.TOYS)
}

